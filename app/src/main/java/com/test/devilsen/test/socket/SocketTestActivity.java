package com.test.devilsen.test.socket;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.Executors;

/**
 * desc :
 * date : 2019-11-25 17:20
 *
 * @author : dongSen
 */
public class SocketTestActivity extends AppCompatActivity {

    private Socket socket;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            newSocket();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                newSocket();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void newSocket() throws Exception {
        InetAddress address = InetAddress.getByName("");
        socket = new Socket(address, 80);

        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
//        writer.write();
//        writer.flush();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String readLine;
        while ((readLine = bufferedReader.readLine()) != null) {
            if ("".equals(readLine)) {
                Log.e("response", readLine);
            }
        }

    }
}
