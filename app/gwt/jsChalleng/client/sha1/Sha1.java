package gwt.jsChalleng.client.sha1;

public class Sha1 {
	public static native String calculate (String msg) /*-{ 
		return $wnd.hex_sha1(msg);
		//return toto;	 
	}-*/;
}
