package handlers;

import java.util.ArrayList;

import factory.ReservaAbstracta;


public class ReservaHandler {
	
	private static ArrayList<ReservaAbstracta> reservesList=new ArrayList<ReservaAbstracta>();
	private static ReservaHandler instance = null;
	
	public static ReservaHandler getInstance() {
        if(ReservaHandler.instance == null) {
        	ReservaHandler.instance = new ReservaHandler();
        }
        return ReservaHandler.instance;
    }
	
	
	
}
