package com.example.asynctaskprogress

import android.content.Context
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var pb : ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>( R.id.btnI ).setOnClickListener{ btnIClick() }
        pb = findViewById( R.id.progressBar )
    }

    fun btnIClick(){
        val i = AsyckTaskPB( this, this )
        i.execute()
        Toast.makeText( baseContext, "asdf", Toast.LENGTH_LONG ).show()
    }

    class AsyckTaskPB ( context: Context, act: MainActivity ) : AsyncTask<Integer, Integer, Integer>(){//background, progressupdate y luego postexecute
        var mCon = context
        var mAct = act
        var pb = mAct.findViewById<ProgressBar>( R.id.progressBar )

        override fun doInBackground(vararg p0: Integer?): Integer {
            //Toast.makeText( , "asdf", Toast.LENGTH_LONG ).show
            Log.d("OK", "PRUEBA")
            pb.max = 100
            var i = 0
            while( i <= 100 ) {
                publishProgress( ++i as Integer )
                Thread.sleep(100)
            }
            return Integer(1)
        }

        override fun onProgressUpdate(vararg values: Integer?) {//recibe onpublishProgress
            Toast.makeText( mCon, "aXXsdf", Toast.LENGTH_LONG ).show()
            //mAct.findViewById<Button>( R.id.btnI ).text = "HERMOSO"
            pb.progress = values[0] as Int
        }

        override fun onPostExecute(result: Integer?) {//recibe de doinbackground
            Toast.makeText( mCon, "Tarea terminada :D", Toast.LENGTH_LONG ).show()
            mAct.findViewById<Button>( R.id.btnI ).text = "Finish!"
        }

    }
}
