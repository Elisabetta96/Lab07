package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.List;
import java.time.Period;


import it.polito.tdp.poweroutages.db.PowerOutageDAO;

public class Model {

	PowerOutageDAO podao;
	List<Po> PoFromNerc;
	List<Po> BestPo = new ArrayList<Po>();
	int bestca;
	int maxY;
	int maxH;
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	public List<Po> getOutagesFromNerc(int idNerc) {
		return podao.getOutagesFromNerc(idNerc);
	}

	public List<Po> calcolaRisultato(int idNerc,int maxY, int maxH) {
		this.PoFromNerc= this.getOutagesFromNerc(idNerc);
		this.BestPo.clear();
		this.bestca=0;
		this.maxY=maxY;
		this.maxH=maxH;
		calcola(new ArrayList<Po>(),0);
		return BestPo;
	}
	
	private void calcola(List<Po> parziale,int l) {
		if (isValid(parziale) || parziale.size()==0) {
			int val=valore(parziale);
			if (val>this.bestca) {
				this.bestca=val;
				this.BestPo.clear();
				this.BestPo.addAll(parziale);
			}
		} else {
			for(int i=l;i<PoFromNerc.size();i++) {
				parziale.add(PoFromNerc.get(i));
				calcola(parziale,l+1);
				parziale.remove(PoFromNerc.get(i));
			}
		}
	}
	
	private boolean isValid(List<Po> possibile) {
        Period period = Period.between(possibile.get(0).getBegan().toLocalDate(),
        		                       possibile.get(possibile.size()-1).getFinished().toLocalDate());

		if (period.getYears() > maxY  ) {
			return false;
		} else {
			long totH=0;
			for(Po po :possibile) {
				totH+=po.getDuration();
			}
			if (totH>maxH) {
				return false;
			}
		}
		return true;
	}
	
	private int valore(List<Po> possibile) {
		int numca=0;
		for(Po po :possibile) {
			numca+=po.getCa();
		}
		return numca;
	}

}
