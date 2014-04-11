package com.cfdigital.cc.ccbasics.constants;

public enum Msg {

		
	ERR_INSUFFPARAMS ("Insufficient parameters."),				
	ERR_NOPLAYER("Invalid player or player is not online."),
	ERR_INSUFFPERMISSION("You don't have the required permission for this command"),
	ERR_NEEDSPLAYER("This command requires player context."),
	NOTICE_BAN_NOTSET("Player was not banned."),
	NOTICE_BAN_SET("Player has been banned."),
	NOTICE_BAN_UNSET("Player has been banned.");
	
    private final String toString;
	
    @Override
    public String toString() {
        return toString;
    }
    
	private Msg(String string) {
		this.toString = new String(string);
	}
	
}