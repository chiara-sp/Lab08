package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {

	private SimpleWeightedGraph<Airport,DefaultWeightedEdge> grafo;
	private ExtFlightDelaysDAO dao;
	private Map<Integer,Airport> idMap;
	
	public Model() {
		dao= new ExtFlightDelaysDAO();
		idMap= new HashMap<>();
		dao.loadAllAirports(idMap);
		
	}
	
	public void creaGrafo(int distanza) {
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		//aggiunta vertici
				//1. -> recupero tutti gli Airport dal db
				//2. li inseerisco come vertici
		
		Graphs.addAllVertices(grafo,idMap.values());
		
		//aggiunta archi
		// devo aggiungere le rotte se distanza media maggiore del valore inserito
		for(Rotta r: dao.getRotte(idMap, distanza)) {
			DefaultWeightedEdge d= grafo.getEdge(r.getPartenza(), r.getDestinazione());
			if(d==null)
				Graphs.addEdge(grafo, r.getPartenza(), r.getDestinazione(), r.getPeso());
			else {
				double peso= grafo.getEdgeWeight(d);
				double newPeso= (peso+r.getPeso())/2;
				grafo.setEdgeWeight(d, newPeso);
				
			}
		}
	}
	public int numVertici() {
		return this.grafo.vertexSet().size();
	}
	public int numArchi() {
		return this.grafo.edgeSet().size();
	}
	public List<Rotta> getRotte(){
		List<Rotta> result= new ArrayList<>();
		for(DefaultWeightedEdge d: this.grafo.edgeSet()) {
			result.add(new Rotta(grafo.getEdgeSource(d),grafo.getEdgeTarget(d),grafo.getEdgeWeight(d)));
		}
		return result;
	}
}
