package com.example.piekno;

import com.piekno.Utils.DiskImageCache;
import com.piekno.Utils.DoubleBackCloseHandler;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
   private boolean title_menu_opened = true;
   private boolean title_menu_able = true;
   private ViewPager main_banner;
   private int main_banner_max, main_banner_position;
   private DoubleBackCloseHandler appcloser;
   private CountDownTimer main_banner_timer;
   private DiskImageCache imageCacher;
   private int Navigations[];
   private ImageButton menubtns[];

   
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
      setContentView(R.layout.activity_main);
      getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title);
      appcloser = new DoubleBackCloseHandler(this);
      Config.setStatusColor(this, Color.rgb(202, 117, 96));
      //Config.setStatusColor(this, Color.rgb(102, 153, 255));
      varSet();
      titleSet();
      mainSet();
      Config.loadingState = 1;
   }

   @Override
   public void onBackPressed() {
      // super.onBackPressed();
      try {
         appcloser.onBackPressed();
      } catch (Exception e) {

      }
   }

   public void varSet() {
      main_banner_max = 5;
      int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
      int cacheSize = maxMemory / 8;

      imageCacher = new DiskImageCache(this);

   }

   public void banner_set_navigation_onoff(int n, boolean on) {
      final ImageButton ib = (ImageButton) findViewById(Navigations[n]);
      if(on){
         ib.setImageDrawable(getDrawable(R.drawable.main_banner_navi_on));
      }else{
         ib.setImageDrawable(getDrawable(R.drawable.main_banner_navi_off));
      }
      /*
      if (on) {
      
          * 이미지가 사라졌나다 나타나서 깜빡이는것처럼 보이는 문제..
          * off 이미지를 5개다 깔아놓고 on 이미지를 겹쳐서 다깔아놓음
          * 각 번호별 on 이미지를 숨겼다 보였다하면서 같이 애니메이션을 주면 자연스러울것같은
          * 느낌적인 느낌
          * 
         final Animation ani_alpha_visible = AnimationUtils.loadAnimation(this, R.anim.alphavisible);
         ib.setImageDrawable(getDrawable(R.drawable.main_banner_navi_on));
         ib.startAnimation(ani_alpha_visible);
      } else {

         ib.setImageDrawable(getDrawable(R.drawable.main_banner_navi_off));
         
         final Animation ani_alpha_hide = AnimationUtils.loadAnimation(this, R.anim.alphahide);
         ib.startAnimation(ani_alpha_hide);
         ani_alpha_hide.setAnimationListener(new AnimationListener() {
            
            @Override
            public void onAnimationStart(Animation animation) {
               // TODO Auto-generated method stub
               
            }
            
            @Override
            public void onAnimationRepeat(Animation animation) {
               // TODO Auto-generated method stub
               
            }
            
            @Override
            public void onAnimationEnd(Animation animation) {
               // TODO Auto-generated method stub
               ib.setVisibility(ib.INVISIBLE);
               ib.setImageDrawable(getDrawable(R.drawable.main_banner_navi_off));
               ib.setVisibility(ib.VISIBLE);
            }
         });
         }
         */
      
   }

   public void main_banner_click(){
      Config.toast(this, (main_banner_position+1)+"번 사진 클릭");
   }
   public class Menu_btn_event implements OnClickListener{

      Activity root;
      public Menu_btn_event(Activity root) {
         this.root = root;
      }
      @Override
      public void onClick(View v) {
         // TODO Auto-generated method stub
         switch(v.getId()){
         case R.id.menu_btn1:
            Config.toast(root, "1번 버튼");
            break;
         case R.id.menu_btn2:
            Config.toast(root, "2번 버튼");
            break;
         case R.id.menu_btn3:
            Config.toast(root, "3번 버튼");
            break;
         case R.id.menu_btn4:
            Config.toast(root, "4번 버튼");
            break;
         case R.id.menu_btn5:
            Config.toast(root, "5번 버튼");
            break;
         case R.id.menu_btn6:
            Config.toast(root, "6번 버튼");
            break;
         case R.id.menu_btn7:
            Config.toast(root, "7번 버튼");
            break;
         case R.id.menu_btn8:
            Config.toast(root, "8번 버튼");
            break;
         default:
            Config.toast(root, "알 수 없는 오류");
            break;
         }
      }
      
   }
   @SuppressWarnings("deprecation")
   public void mainSet() {
      Navigations = new int[5];
      Navigations[0] = R.id.main_banner_navi1;
      Navigations[1] = R.id.main_banner_navi2;
      Navigations[2] = R.id.main_banner_navi3;
      Navigations[3] = R.id.main_banner_navi4;
      Navigations[4] = R.id.main_banner_navi5;
      menubtns = new ImageButton[8];
      menubtns[0] = (ImageButton)findViewById(R.id.menu_btn1);
      menubtns[1] = (ImageButton)findViewById(R.id.menu_btn2);
      menubtns[2] = (ImageButton)findViewById(R.id.menu_btn3);
      menubtns[3] = (ImageButton)findViewById(R.id.menu_btn4);
      menubtns[4] = (ImageButton)findViewById(R.id.menu_btn5);
      menubtns[5] = (ImageButton)findViewById(R.id.menu_btn6);
      menubtns[6] = (ImageButton)findViewById(R.id.menu_btn7);
      menubtns[7] = (ImageButton)findViewById(R.id.menu_btn8);
      
      Menu_btn_event menu_btn_event = new Menu_btn_event(this);
      menubtns[0].setOnClickListener(menu_btn_event);
      menubtns[1].setOnClickListener(menu_btn_event);
      menubtns[2].setOnClickListener(menu_btn_event);
      menubtns[3].setOnClickListener(menu_btn_event);
      menubtns[4].setOnClickListener(menu_btn_event);
      menubtns[5].setOnClickListener(menu_btn_event);
      menubtns[6].setOnClickListener(menu_btn_event);
      menubtns[7].setOnClickListener(menu_btn_event);
      
      
      imageCacher = new DiskImageCache(this);
      main_banner = (ViewPager) findViewById(R.id.main_banner);
      main_banner.setAdapter(new MainPagerAdapter(getApplicationContext()));
      main_banner.setOnTouchListener(new View.OnTouchListener() {
          float oldX = 0, newX = 0, sens = 5;

          @Override
          public boolean onTouch(View v, MotionEvent event) {
              switch (event.getAction()) {
              case MotionEvent.ACTION_DOWN:
                  oldX = event.getX();
                  break;

              case MotionEvent.ACTION_UP:
                  newX = event.getX();
                  if (Math.abs(oldX - newX) < sens) {
                     main_banner_click();
                      return true;
                  }
                  oldX = 0;
                  newX = 0;
                  break;
              }

              return false;
          }
      });
      main_banner.setOnPageChangeListener(new OnPageChangeListener() {

         
         @Override
         public void onPageSelected(int position) {
            banner_set_navigation_onoff(main_banner_position, false);
            banner_set_navigation_onoff(position, true);
            main_banner_position = position;
            main_banner_timer.cancel();
            main_banner_timer.start();
         }

         @Override
         public void onPageScrolled(int arg0, float arg1, int arg2) {
         }

         @Override
         public void onPageScrollStateChanged(int arg0) {
         }
      });
      main_banner_timer = new CountDownTimer(5000, 5000) {

         @Override
         public void onTick(long millisUntilFinished) {
            // TODO Auto-generated method stub

         }

         @Override
         public void onFinish() {
            if (main_banner_position == main_banner_max - 1) {
               main_banner.setCurrentItem(0);
            } else {
               main_banner.setCurrentItem(main_banner_position + 1);
            }
         }
      };
      main_banner_timer.start();
   }

   public class MainPagerAdapter extends PagerAdapter {
      private LayoutInflater Inflater;

      public MainPagerAdapter(Context c) {
         super();
         Inflater = LayoutInflater.from(c);
      }

      @Override
      public int getCount() {
         return main_banner_max;
      }

      @SuppressLint("InflateParams")
      @Override
      public Object instantiateItem(View pager, int position) {
         View v = null;

         if (position < 0 || main_banner_max <= position) {
            return null;
         }
         v = Inflater.inflate(R.layout.main_banner_img, null);
         ImageView iv = (ImageView) v.findViewById(R.id.main_banner_imgView);
         if (position == 0) {
            iv.setImageResource(R.drawable.main_back);
         } else {
            // iv.setImageResource(R.drawable.ic_launcher);
            // new
            // DownlaodImageInBackground(iv).execute(Config.main_banner_img_url[position]);
            // Bitmap bit =
            // imageCacher.loadImageByUrl(Config.main_banner_img_url[position]);
            // iv.setImageBitmap(bit);
            imageCacher.setProblemImage(iv, Config.main_banner_img_url[position]);
            /*
             * try{ URL url = new URL(Config.main_banner_img_url[position]);
             * URLConnection conn = url.openConnection(); conn.connect();
             * 
             * int nSize = conn.getContentLength(); BufferedInputStream bis
             * = new BufferedInputStream(conn.getInputStream(), nSize);
             * Bitmap bitmap = BitmapFactory.decodeStream(bis);
             * iv.setImageBitmap(bitmap); }catch(Exception e){
             * e.printStackTrace(); }
             */

         }
         ((ViewPager) pager).addView(v, 0);
         return v;
      }

      @Override
      public void destroyItem(View pager, int position, Object view) {
         ((ViewPager) pager).removeView((View) view);
      }

      @Override
      public boolean isViewFromObject(View pager, Object obj) {
         return pager == obj;
      }

   }// end of inner class

   public void title_menu_animation() {
      CountDownTimer cdt = new CountDownTimer(150, 70) {
         ImageButton ib = (ImageButton) findViewById(R.id.title_Btn_menu);

         @Override
         public void onTick(long millisUntilFinished) {
            // TODO Auto-generated method stub
            ib.setImageDrawable(getDrawable(R.drawable.menu_btn_dropdown_mid));
         }

         @Override
         public void onFinish() {
            if (title_menu_opened) {
               ib.setImageDrawable(getDrawable(R.drawable.menu_btn_dropdown_up));
            } else {
               ib.setImageDrawable(getDrawable(R.drawable.menu_btn_dropdown_down));
            }
            title_menu_able = true;
         }
      };
      cdt.start();
   }

   public void titleSet() {
      // Button title_btn_menu = (Button)findViewById(R.id.title_Btn_menu);
      // Button title_btn_back = (Button)findViewById(R.id.title_Btn_Back);
      ImageButton title_btn_menu = (ImageButton) findViewById(R.id.title_Btn_menu);
      ImageButton title_btn_back = (ImageButton) findViewById(R.id.title_Btn_home);

      final Animation ani_riseup = AnimationUtils.loadAnimation(this, R.anim.riseup);
      final Animation ani_dropdown = AnimationUtils.loadAnimation(this, R.anim.dropdown);
      ani_riseup.setAnimationListener(new AnimationListener() {
         LinearLayout ll = (LinearLayout) findViewById(R.id.title_menu_pan);

         @Override
         public void onAnimationStart(Animation animation) {
         }

         @Override
         public void onAnimationRepeat(Animation animation) {
         }

         @Override
         public void onAnimationEnd(Animation animation) {
            if (!title_menu_opened) {
               ll.setVisibility(View.GONE);
            }
         }
      });
      title_btn_back.setOnClickListener(new OnClickListener() {

         @Override
         public void onClick(View arg0) {
            startActivity(new Intent(getApplication(), LoginActivity.class));
            MainActivity.this.finish();
         }

      });
      title_btn_menu.setOnClickListener(new Button.OnClickListener() {
         @Override
         public void onClick(View v) {
            LinearLayout ll = (LinearLayout) findViewById(R.id.title_menu_pan);
            // Button btn = (Button)findViewById(R.id.title_Btn_menu);
            ImageButton btn = (ImageButton) v;
            if (title_menu_able) {
               title_menu_able = false;
               if (title_menu_opened) {
                  ll.startAnimation(ani_riseup);
                  title_menu_opened = false;
                  // btn.setText(" ▼ ");
                  // btn.setImageDrawable(getDrawable(R.drawable.menu_btn_dropdown_down));
                  title_menu_animation();
                  // ll.setVisibility(View.GONE);
               } else {
                  ll.setVisibility(View.VISIBLE);
                  ll.startAnimation(ani_dropdown);
                  title_menu_opened = true;
                  // btn.setImageDrawable(getDrawable(R.drawable.menu_btn_dropdown_up));
                  title_menu_animation();
                  // btn.setText(" ▲ ");
               }
            }
         }
      });
   }

   /*
    * public void use_fillview(){ GridLayout gl =
    * (GridLayout)findViewById(R.id.menus); ViewTreeObserver vto =
    * gl.getViewTreeObserver(); vto.addOnGlobalLayoutListener(new
    * OnGlobalLayoutListener() {@Override public void onGlobalLayout() {
    * 
    * GridLayout gl = (GridLayout) findViewById(R.id.menus); fillview(gl);
    * 
    * ViewTreeObserver obs = gl.getViewTreeObserver();
    * obs.removeGlobalOnLayoutListener(this); }}); Button title_btn_menu =
    * (Button)findViewById(R.id.title_Btn_menu);
    * 
    * } public void fillview(GridLayout gl) { Button buttontemp;
    * 
    * //Stretch buttons int idealChildWidth = (int)
    * ((gl.getWidth()-1*gl.getColumnCount())/gl.getColumnCount()); for( int
    * i=0; i< gl.getChildCount();i++) { buttontemp = (Button) gl.getChildAt(i);
    * buttontemp.setWidth(idealChildWidth); } }
    */
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.main, menu);
      return true;
   }

   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
      // Handle action bar item clicks here. The action bar will
      // automatically handle clicks on the Home/Up button, so long
      // as you specify a parent activity in AndroidManifest.xml.
      int id = item.getItemId();
      if (id == R.id.action_settings) {
         return true;
      }
      return super.onOptionsItemSelected(item);
   }
}