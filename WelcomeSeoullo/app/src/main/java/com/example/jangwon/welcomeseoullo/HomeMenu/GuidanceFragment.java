package com.example.jangwon.welcomeseoullo.HomeMenu;

import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.jangwon.welcomeseoullo.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
//메인에서 이용안내 및 시간
public class GuidanceFragment extends Fragment{
    //리플릿다운로드?부분 보여주고 안내시간 이용 시간은 이미지뷰로 나타냄
    View view;
    Button korean;
    Button english;
    Button japanese;
    Button chinese;
    Button guideDownload;
    String DownloadURL;
    String FileName;
    private final int  MEGABYTE = 1024 * 1024;

    public GuidanceFragment(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        view = inflater.inflate(R.layout.fragment_guidance, container, false);

        korean = (Button) view.findViewById(R.id.btn_korean);
        english = (Button) view.findViewById(R.id.btn_english);
        japanese = (Button) view.findViewById(R.id.btn_japan);
        chinese = (Button) view.findViewById(R.id.btn_china);
        guideDownload = (Button) view.findViewById(R.id.btn_guide);

        korean.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                DownloadURL = "http://seoullo7017.seoul.go.kr/files/7017leaflet_korean.pdf";
                FileName = "7017leaflet_korean.pdf";
                new DownloadFile().execute(DownloadURL,FileName);
                LoadFile(DownloadURL,FileName);
            }
        });
        english.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                DownloadURL = "http://seoullo7017.seoul.go.kr/files/7017leaflet_english.pdf";
                FileName = "7017leaflet_english.pdf";
                new DownloadFile().execute(DownloadURL,FileName);
                LoadFile(DownloadURL,FileName);
            }
        });
        chinese.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                DownloadURL = "http://seoullo7017.seoul.go.kr/files/7017leaflet_chinese.pdf";
                FileName = "7017leaflet_chinese.pdf";
                new DownloadFile().execute(DownloadURL,FileName);
                LoadFile(DownloadURL,FileName);
            }
        });
        japanese.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                DownloadURL = "http://seoullo7017.seoul.go.kr/files/7017leaflet_japanese.pdf";
                FileName = "7017leaflet_japanese.pdf";
                new DownloadFile().execute(DownloadURL,FileName);
                LoadFile(DownloadURL,FileName);
            }
        });
        guideDownload.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                DownloadURL = "http://seoullo7017.seoul.go.kr/files/7017_walking_tour_map.pdf";
                FileName = "7017_walking_tour_map.pdf";
                new DownloadFile().execute(DownloadURL,FileName);
                LoadFile(DownloadURL,FileName);
            }
        });

        return view;
    }

    public void LoadFile(String url, String file)
    {
        Toast.makeText(getActivity().getApplicationContext(),file+"다운로드", Toast.LENGTH_SHORT).show();
        File pdfFile = new File(Environment.getExternalStorageDirectory() +"/Download/"+ file);  // -> filename = maven.pdf
        if(pdfFile.exists()) {
            Uri path = Uri.fromFile(pdfFile);
            Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
            pdfIntent.setDataAndType(path, "application/pdf");
            pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            try {
                startActivity(pdfIntent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(getActivity().getApplicationContext(), "No Application available to view PDF", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        }
    }
    public class DownloadFile extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            String fileUrl = strings[0];   // -> http://maven.apache.org/maven-1.x/maven.pdf
            String fileName = strings[1];  // -> maven.pdf
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            File folder = new File(extStorageDirectory, "/Download/");
            folder.mkdir();

            File pdfFile = new File(folder, fileName);

            try{
                pdfFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
            downloadFile(fileUrl, pdfFile);
            return null;
        }
    }

    public void downloadFile(String fileUrl, File directory){
        try {

            URL url = new URL(fileUrl);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            //urlConnection.setRequestMethod("GET");
            //urlConnection.setDoOutput(true);
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream(directory);
            int totalSize = urlConnection.getContentLength();

            byte[] buffer = new byte[MEGABYTE];
            int bufferLength = 0;
            while((bufferLength = inputStream.read(buffer))>0 ){
                fileOutputStream.write(buffer, 0, bufferLength);
            }
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}