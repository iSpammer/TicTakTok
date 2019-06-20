package ispam.mark.xoro;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
//    private ImageView img1, img2, img3, img4, img5, img6, img7, img8, img9;
    private GridLayout gridLayout;
    private TextView title;
    private Player[] playerChoice = new Player[9];
    private int[][] winnerState = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    private boolean winState = false;
    enum Player {
        ONE, TWO
    }
    private Player currPlayer = Player.ONE;
    private Button btnReset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        img1 = findViewById(R.id.img1);
//        img2 = findViewById(R.id.img2);
//        img3 = findViewById(R.id.img3);
//        img4 = findViewById(R.id.img4);
//        img5 = findViewById(R.id.img5);
//        img6 = findViewById(R.id.img6);
//        img7 = findViewById(R.id.img7);
//        img8 = findViewById(R.id.img8);
//        img9 = findViewById(R.id.img9);
        title = findViewById(R.id.title);
        gridLayout = findViewById(R.id.gridLayout);
        btnReset = findViewById(R.id.reset);
//        btnReset.setVisibility(INVISIBLE);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });
    }

    public void imageOnTap(View imageView){
        if(winState){
            Toast.makeText(this,"Game Over", Toast.LENGTH_SHORT).show();
            return;
        }
        ImageView tappedView = (ImageView) imageView;
        int index = Integer.parseInt(imageView.getTag().toString());
        if(playerChoice[index]!=null){
            Toast.makeText(this,"Cell Occupied", Toast.LENGTH_SHORT).show();
            return;
        }
        tappedView.setTranslationX(-2000);
        playerChoice[index] = currPlayer;
//        int[] mark = getXYLocation(imageView);
        if(currPlayer == Player.ONE) {
            tappedView.setImageResource(R.drawable.xicon);
//            markLoc(mark, currPlayer);
            currPlayer = Player.TWO;
        }
        else{
            tappedView.setImageResource(R.drawable.oicon);
//            markLoc(mark, currPlayer);
            currPlayer = Player.ONE;
        }
//        for(int i = 0; i < mapPlayer.length; i++){
//    //        if(m)
//            for(int j = 0; j < mapPlayer.length; j++){
//
//            }
//        }
        tappedView.animate().translationXBy(2000).alpha(1f).rotation(3600).setDuration(1000);
//        Toast.makeText(this, imageView.getId()+"hi", Toast.LENGTH_SHORT);
//        if(R.id.img1 == tappedView.getId()){
//            Log.d("CLICKED", "imageOnTap: "+imageView.getId());
//
//        }
//        Log.d("RAR", "imageOnTap: "+ Arrays.toString(playerChoice));
        for(int[] winCol: winnerState ){
            if(playerChoice[winCol[0]] == playerChoice[winCol[1]] && playerChoice[winCol[1]] == playerChoice[winCol[2]] && playerChoice[winCol[1]]!=null){
                String winner;
                if(currPlayer == Player.ONE)
                    winner = "Two";
                else
                    winner = "One";
                Toast.makeText(this, "Winner Winner Chicken Dinner\n Player "+winner+" WON!", Toast.LENGTH_SHORT).show();
                title.setText("Player "+winner+" Won!");
                winState = true;
                btnReset.setVisibility(View.VISIBLE);
                break;
            }
        }
    }
//
//    private void markLoc(int[] mark, Player currPlayer) {
//        for(int i = 0; i < mapPlayer.length; i ++){
//            if(i == mark[0]){
//                for(int j = 0; j < mapPlayer.length; j ++){
//                    if(j == mark[1]){
//                        if(currPlayer == Player.ONE)
//                            mapPlayer[i][j] = 1;
//                        else
//                            mapPlayer[i][j] = 2;
//                        break;
//                    }
//                }
//            }
//        }
//    }

    private int[] getXYLocation(View view){
        int[] loc = new int[2];
        if(view.getId() == R.id.img1){
            return loc;
        }
        else if(view.getId() == R.id.img2){
            loc[1] = 1;
            return loc;
        }
        else if(view.getId() == R.id.img3){
            loc[1] = 2;
            return loc;
        }
        else if(view.getId() == R.id.img4){
            loc[0] = 1;
            return loc;
        }
        else if(view.getId() == R.id.img5){
            loc[0] = 1;
            loc[1] = 1;
            return loc;
        }
        else if(view.getId() == R.id.img6){
            loc[0] = 1;
            loc[1] = 2;
            return loc;
        }
        else if(view.getId() == R.id.img7){
            loc[0] = 2;
            return loc;
        }
        else if(view.getId() == R.id.img8){
            loc[0] = 2;
            loc[1] = 1;
            return loc;
        }
        else {
            loc[0] = 2;
            loc[1] = 2;
            return loc;
        }
    }

    private void reset(){
        currPlayer = Player.ONE;
        for(int i = 0; i < gridLayout.getChildCount(); i++){
            ((ImageView)gridLayout.getChildAt(i)).setImageDrawable(null);
            gridLayout.getChildAt(i).animate().alpha(0.2f).rotation(0);
        }
        for(int i = 0; i < playerChoice.length; i ++){
            playerChoice[i] = null;
        }
        title.setText("X or O?");
        winState = false;
        btnReset.setVisibility(View.GONE);
    }
}
