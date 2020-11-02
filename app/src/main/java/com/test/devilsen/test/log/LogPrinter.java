package com.test.devilsen.test.log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import static com.test.devilsen.test.log.ELog.ASSERT;
import static com.test.devilsen.test.log.ELog.DEBUG;
import static com.test.devilsen.test.log.ELog.ERROR;
import static com.test.devilsen.test.log.ELog.INFO;
import static com.test.devilsen.test.log.ELog.VERBOSE;
import static com.test.devilsen.test.log.ELog.WARN;
import static com.test.devilsen.test.log.ELog.isEnable;
import static com.test.devilsen.test.log.Utils.checkNotNull;

/**
 * desc : Logger printer
 * date : 2020/10/15 2:48 PM
 *
 * @author : orhanobut
 */
class LogPrinter {

    /**
     * It is used for json pretty print
     */
    private static final int JSON_INDENT = 2;

    private final LogFormatter mFormatter = new LogFormatter();

    public void d(@NonNull String message, @Nullable Object... args) {
        log(DEBUG, null, message, args);
    }

    public void d(@NonNull String message) {
        log(DEBUG, null, message);
    }

    public void d(@Nullable Object object) {
        log(DEBUG, null, Utils.toString(object));
    }

    public void e(@NonNull String message, @Nullable Object... args) {
        e(null, message, args);
    }

    public void e(@Nullable Throwable throwable, @NonNull String message, @Nullable Object... args) {
        log(ERROR, throwable, message, args);
    }

    public void w(@NonNull String message, @Nullable Object... args) {
        log(WARN, null, message, args);
    }

    public void i(@NonNull String message, @Nullable Object... args) {
        log(INFO, null, message, args);
    }

    public void v(@NonNull String message, @Nullable Object... args) {
        log(VERBOSE, null, message, args);
    }

    public void wtf(@NonNull String message, @Nullable Object... args) {
        log(ASSERT, null, message, args);
    }

    public void json(@Nullable String json) {
        if (Utils.isEmpty(json)) {
            d("Empty/Null json content");
            return;
        }
        try {
            json = json.trim();
            if (json.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(json);
                String message = jsonObject.toString(JSON_INDENT);
                d(message);
                return;
            }
            if (json.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(json);
                String message = jsonArray.toString(JSON_INDENT);
                d(message);
                return;
            }
            e("Invalid Json");
        } catch (JSONException e) {
            e("Invalid Json");
        }
    }

    public void xml(@Nullable String xml) {
        if (Utils.isEmpty(xml)) {
            d("Empty/Null xml content");
            return;
        }
        try {
            Source xmlInput = new StreamSource(new StringReader(xml));
            StreamResult xmlOutput = new StreamResult(new StringWriter());
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(xmlInput, xmlOutput);
            d(xmlOutput.getWriter().toString().replaceFirst(">", ">\n"));
        } catch (TransformerException e) {
            e("Invalid xml");
        }
    }

    public synchronized void log(int priority,
                                 @Nullable String tag,
                                 @Nullable String message,
                                 @Nullable Throwable throwable) {
        if (throwable != null && message != null) {
            message += " : " + Utils.getStackTraceString(throwable);
        }
        if (throwable != null && message == null) {
            message = Utils.getStackTraceString(throwable);
        }
        if (Utils.isEmpty(message)) {
            message = "Empty/NULL log message";
        }

        if (isEnable()) {
            mFormatter.log(priority, tag, message);
        }
    }

    /**
     * This method is synchronized in order to avoid messy of logs' order.
     */
    private synchronized void log(int priority,
                                  @Nullable Throwable throwable,
                                  @NonNull String msg,
                                  @Nullable Object... args) {
        checkNotNull(msg);

        String tag = getTag();
        String message = createMessage(msg, args);
        log(priority, tag, message, throwable);
    }

    /**
     * @return the appropriate tag based on local or global
     */
    @NonNull
    private String getTag() {
        return ELog.E_TAG;
    }

    @NonNull
    private String createMessage(@NonNull String message, @Nullable Object... args) {
        return args == null || args.length == 0 ? message : String.format(message, args);
    }

    public void showThreadInfo() {
        mFormatter.setShowThreadInfo();
    }

    public void showMethodInfo() {
        mFormatter.setMethodCount();
    }
}
