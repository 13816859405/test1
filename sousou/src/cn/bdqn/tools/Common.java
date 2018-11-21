package cn.bdqn.tools;

import java.text.DecimalFormat;

public class Common {

	public static String dataFormat(double money){
		DecimalFormat df = new DecimalFormat("#.0");
		return df.format(money);
	}
}
