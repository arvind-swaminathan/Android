package patrol.npd;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class User extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        TextView test=(TextView)findViewById(R.id.text1);
        final SurfaceHolder holder=((SurfaceView)findViewById(R.id.surfaceView)).getHolder();
        final MediaPlayer mediaPlayer = new MediaPlayer();
        //holder.addCallback(User.this);
        Intent intent=getIntent();
        test.setText(intent.getStringExtra("user_name"));

      //  VideoView mVideoView = (VideoView)findViewById(R.id.videoview);
        Button playVideo=(Button)findViewById(R.id.playVideo);

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
                public void onCompletion(MediaPlayer mp)
                {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                }

        });

                playVideo.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if (!(mediaPlayer.isPlaying())) {
                            String url = "http://techslides.com/demos/sample-videos/small.mp4";
                            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                            try {
                                mediaPlayer.setDataSource(url);
                                mediaPlayer.setDisplay(holder);
                                mediaPlayer.prepare(); // might take long! (for buffering, etc)
                                mediaPlayer.start();
                            } catch (Exception e) {
                                Toast.makeText(User.this,
                                        "Unable to play video", Toast.LENGTH_LONG)
                                        .show();
                            }
                        }


                    }
                });


            }


            @Override
            public boolean onCreateOptionsMenu(Menu menu) {
                // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater().inflate(R.menu.menu_user, menu);
                return true;
            }

            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                // Handle action bar item clicks here. The action bar will
                // automatically handle clicks on the Home/Up button, so long
                // as you specify a parent activity in AndroidManifest.xml.
                int id = item.getItemId();

                //noinspection SimplifiableIfStatement
                if (id == R.id.action_settings) {
                    return true;
                }

                return super.onOptionsItemSelected(item);
            }
        }
