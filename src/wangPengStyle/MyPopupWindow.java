package wangPengStyle;

/*
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
*/

public class MyPopupWindow {
	
	TextView open, close, getnew;
	View parent, view;
	Activity ctx;
	PopupWindow pw;
	ListView lv;
	String tag = "MyPopupWindow";
	
	public MyPopupWindow(View parent, Activity ctx){
		this.parent = parent;
		this.ctx = ctx;
	}
	
	public void show(){
		if(pw == null){
			LayoutInflater layoutInflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.popup_window, null);
            int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);  
            int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);  
            view.measure(w, h);  
            int height =view.getMeasuredHeight();  
            int width =view.getMeasuredWidth();  
            Log.i(tag, "width-"+width +" height-"+height);
            // ����һ��PopuWidow����
            pw = new PopupWindow(view, width, height);
        }
		open = (TextView)view.findViewById(R.id.title_openvpn);
		close = (TextView)view.findViewById(R.id.title_closevpn);
		getnew = (TextView)view.findViewById(R.id.title_getnew);
		
		open.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Toast.makeText(ctx, ctx.toString()+"vpn open", Toast.LENGTH_SHORT).show();
			}
		});
		
		close.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Toast.makeText(ctx, ctx.toString()+"VPN close", Toast.LENGTH_SHORT).show();
			}
		});
		
		getnew.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Toast.makeText(ctx, ctx.toString()+"��ȡ���豸�¼�", Toast.LENGTH_SHORT).show();
			}
		});
		
        // ʹ��ۼ�
		pw.setFocusable(true);
        // ����������������ʧ
		pw.setOutsideTouchable(true);
		pw.setTouchInterceptor(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i("mengdd", "onTouch : ");
                return false;
                // �����������true�Ļ���touch�¼���������
                // ���غ� PopupWindow��onTouchEvent�������ã���������ⲿ�����޷�dismiss
            }
        });

        // �����Ϊ�˵��������Back��Ҳ��ʹ����ʧ�����Ҳ�����Ӱ����ı���
		// ���������PopupWindow�ı����������ǵ���ⲿ������Back�����޷�dismiss����
        pw.setBackgroundDrawable(ctx.getResources().getDrawable(R.drawable.main_skin1));

        // ��ʾ��X��λ���Լ�Y��λ��
        int xPos = 0-pw.getWidth()/2;
        Log.i("coder", "xPos:" + xPos);
        pw.showAsDropDown(parent, xPos, 0);
		}
	
}
