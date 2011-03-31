package com.coolmodev.arraylisttest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	private EditText text1;
	private EditText text2;
	private EditText text3;

	private ArrayList<String> data = new ArrayList<String>();
	
	private static String filename = "settings";
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        text1 = (EditText)findViewById(R.id.text1);
        text2 = (EditText)findViewById(R.id.text2);
        text3 = (EditText)findViewById(R.id.text3);
        
        data.add(text1.getText().toString());
        data.add(text2.getText().toString());
        data.add(text3.getText().toString());
        
        FileInputStream fis = null;
        ObjectInputStream in = null;
                	
      
			try {
				fis = openFileInput(filename);
				in = new ObjectInputStream(fis);
				data = (ArrayList<String>) in.readObject();
				in.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (StreamCorruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		 
        
        if (data.size() >=3) {
        	
			if (data.get(0) != null) {
				text1.setText(data.get(0));
			}
			if (data.get(1) != null) {
				text2.setText(data.get(1));
			}
			if (data.get(2) != null) {
				text3.setText(data.get(2));
			}
        }
    }
    
    
    public void saveData(View v) {
    	
    	data.set(0, text1.getText().toString());
    	data.set(1, text2.getText().toString());
    	data.set(2, text3.getText().toString());    	
    	
    	FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = this.openFileOutput(filename, MODE_PRIVATE);
			out = new ObjectOutputStream(fos);
			out.writeObject(data);
			out.close();
			System.out.println("Object Persisted");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
    }
}