package com.cfdigital.cc.ccbasics.misc;

public enum Msg {

	ERR_INSUFFPARAMS {
		public String toString() {
			return "Insufficient parameters";
		}		
	},
	
	ERR_NOPLAYER {
		public String toString() {
			return "Player is not online";
		}		
	},
	
	ERR_INSUFFPERMISSION {
		public String toString() {
			return "You don't have the required permission for this command";
		}		
	},
	
	ERR_NEEDSPLAYER {
		public String toString() {
			return "This command requires player context";
		}		
	}
	
}