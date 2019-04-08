package it.polito.dp2.rest.nfv.services;

import it.polito.dp2.rest.nfv.db.NsDB;
import it.polito.dp2.rest.nfv.jaxb.*;

import javax.ws.rs.core.UriBuilder;
import java.math.BigInteger;
import java.net.URI;
import java.util.HashMap;

public class NsServices {
    private NsDB nsDB = NsDB.getNsdDB();
    private Pagination pg = new Pagination();
    private int PAGE_SIZE = pg.getPageSize();

    /**
     * Get all the virtualized NSD
     * @param baseURI: the base URI for that resources
     * @param page: the requested page
     * @return The NS
     */
    public NS getNS(String baseURI, int page) {
        NS ns = nsDB.getNS();
        NS pageNS = new NS();
        URI next = null;
        String path = "ns";
        int pageNum = 0, totPage = 0;

        //do pagination only if exists at least one NSD
        if(ns.getNSD().size() == 0){
            ns.setPage(BigInteger.valueOf(1));
            ns.setTotalPages(BigInteger.valueOf(1));
            ns.setNext(UriBuilder.fromUri(baseURI).path(path).queryParam("page", 1).build().toString());
            return ns;
        }

        /* Map the NSD id into an array list.
           I will use integer to access to the data structure because is more easy to do.
           In order to remember the real ID of that NSD i will map them into an array list.
           In that list at each index will correspond a string that is the real ID for that NSD.
           Moreover, the access to the list inside the JAXB is given by index: so with this approach is really easy
           to access what you're looking for.
         */
        pg.mapNSDId(ns);

        // Set actual page number and total number of pages
        pageNum = pg.getPage(page);

        int sizeNS = ns.getNSD().size(); //number of NSD inside the NS
        HashMap sizeMapNSD = new HashMap<Integer, Integer>(); //key: sequential nsd ID, value: number of elements

        int id = 0;
        int totsize = 0;
        for(NSD nsd : ns.getNSD()){ //for each NSD get the related number of elements it owns
            int tmp = 0;

            tmp = pg.getNSDSize(nsd, null); //nsdStruct is not needed, so just put null
            totsize += tmp;

            sizeMapNSD.put(id, tmp);
            id++;
        }
        totPage = pg.getTotPage(totsize);

        //based on the actual page i'm searching which elements i have to show (related to a specific NSD)
        id = 0;
        boolean find = false;
        for(int i=0; i<sizeNS && !find; i++){
            int tmp = (int)sizeMapNSD.get(i);
            for(int j=0; j<i; j++) //sum of the of size of the NSD that im considering and all its previous NSD
                tmp += (int)sizeMapNSD.get(j);

            //based on the above sum i get the id of the NSD i need to consider for this page
            if((pageNum-1)*PAGE_SIZE < tmp){
                id = i;
                find = true;
            }
        }

        if(!find)
            id = sizeNS-1;

        //now i have to know the specific number of elements of that NSD
        NSD nsd = ns.getNSD().get(id);
        int sizeVNFDep = nsd.getVNFDependency().getGraph().size();
        int sizeProperty = nsd.getPropertyDefinition().getProperty().size();
        int sizeVNF = nsd.getVNF().getVNFD().size();
        int sizeVNFFGD = nsd.getVNFFGD().getNetworkForwardingPaths().size();
        int sizePNF = nsd.getPNF().getPNFD().size();
        int sizeFlav = nsd.getFlavours().getServiceDeploymentFlavour().size();
        int sizeCPS = nsd.getConnectionPoints().getConnectionPoint().size();

        // Pagination
        NSD nsdTmp = initNSD(pg.getNSDId(id)); //re-map the id before assign it
        int offset = pg.getNSDOffset(id, sizeMapNSD);
        for(int i=(pageNum-1)*PAGE_SIZE-offset, j=0; i<totsize && j<PAGE_SIZE; i++){
            System.out.print("id: " + id + ", i: " + i);
            if(i < sizeVNFDep){
                nsdTmp.getVNFDependency().getGraph().add(nsd.getVNFDependency().getGraph().get(i));
                j++;
            }
            else if(i >= sizeVNFDep && i < sizeVNFDep+sizeProperty){
                nsdTmp.getPropertyDefinition().getProperty().add(nsd.getPropertyDefinition().getProperty().get(i-sizeVNFDep));
                j++;
            }
            else if(i >= sizeVNFDep+sizeProperty && i < sizeVNFDep+sizeProperty+sizeVNF){
                nsdTmp.getVNF().getVNFD().add(nsd.getVNF().getVNFD().get(i-sizeVNFDep-sizeProperty));
                j++;
            }
            else if(i >= sizeVNFDep+sizeProperty+sizeVNF && i < sizeVNFDep+sizeProperty+sizeVNF+sizeVNFFGD){
                nsdTmp.getVNFFGD().getNetworkForwardingPaths().add(nsd.getVNFFGD().getNetworkForwardingPaths().get(i-sizeVNFDep-sizeProperty-sizeVNF));
                j++;
            }
            else if(i >= sizeVNFDep+sizeProperty+sizeVNF+sizeVNFFGD && i < sizeVNFDep+sizeProperty+sizeVNF+sizeVNFFGD+sizePNF){
                nsdTmp.getPNF().getPNFD().add(nsd.getPNF().getPNFD().get(i-sizeVNFDep-sizeProperty-sizeVNF-sizeVNFFGD));
                j++;
            }
            else if(i >= sizeVNFDep+sizeProperty+sizeVNF+sizeVNFFGD+sizePNF && i < sizeVNFDep+sizeProperty+sizeVNF+sizeVNFFGD+sizePNF+sizeFlav){
                nsdTmp.getFlavours().getServiceDeploymentFlavour().add(nsd.getFlavours().getServiceDeploymentFlavour().get(i-sizeVNFDep-sizeProperty-sizeVNF-sizeVNFFGD-sizePNF));
                j++;
            }
            else if(i >= sizeVNFDep+sizeProperty+sizeVNF+sizeVNFFGD+sizePNF+sizeFlav && i < sizeVNFDep+sizeProperty+sizeVNF+sizeVNFFGD+sizePNF+sizeFlav+sizeCPS){
                nsdTmp.getConnectionPoints().getConnectionPoint().add(nsd.getConnectionPoints().getConnectionPoint().get(i-sizeVNFDep-sizeProperty-sizeVNF-sizeVNFFGD-sizePNF-sizeFlav));
                j++;
            }
            else{
                //if no one of above it means that i'm on the next NSD (i finished to analyze the actual one)
                if(id == sizeNS-1) //if there is no next NSD, just finished
                    break;
                else{ //i need to update nsd and its related elements size
                    pageNS.getNSD().add(nsdTmp); //add the finished NSD to the paged NS

                    id = id+1; //update the id and create the new NSD to be paginated
                    offset = pg.getNSDOffset(id, sizeMapNSD);
                    i = i-offset-1; //-1 only because the for will do i++ at the end of the else

                    nsdTmp = initNSD(pg.getNSDId(id)); //re-map the id before assign it

                    //update the NSD from which to take information
                    nsd = ns.getNSD().get(id);
                    sizeVNFDep = nsd.getVNFDependency().getGraph().size();
                    sizeProperty = nsd.getPropertyDefinition().getProperty().size();
                    sizeVNF = nsd.getVNF().getVNFD().size();
                    sizeVNFFGD = nsd.getVNFFGD().getNetworkForwardingPaths().size();
                    sizePNF = nsd.getPNF().getPNFD().size();
                    sizeFlav = nsd.getFlavours().getServiceDeploymentFlavour().size();
                    sizeCPS = nsd.getConnectionPoints().getConnectionPoint().size();
                }
            }
        }

        pageNS.getNSD().add(nsdTmp);

        pageNS.setTotalPages(BigInteger.valueOf(totPage));
        pageNS.setPage(BigInteger.valueOf(pageNum));

        next = pg.getNextPage(baseURI, path, pageNum, totPage);
        pageNS.setNext(next.toString());

        return pageNS;
    }

    /**
     * Add a NS list
     * @param ns: the NS to be added
     * @return The NS
     */
    public NS addNS(NS ns) {
        return nsDB.addNS(ns);
    }

    /**
     * Delete all NS
     */
    public void deleteNS() {
        nsDB.deleteNS();
    }

    /**
     * Get the info of a NSD
     * @param nsdID: the ID of the considered NSD
     * @param baseURI: base URI for that resources
     * @param page: the requested page
     * @return The NSD or null if the NSD does not exist
     */
    public NSD getNSDInfo(String nsdID, String baseURI, int page){
        NSD nsd = nsDB.getNSDInfo(nsdID);
        NSD pageNSD = initNSD(nsdID);
        NSDStruct nsdStruct = new NSDStruct();
        URI next = null;
        String path = "ns/nsd/" + nsdID;
        int pageNum = 0, totPage = 0;

        //do pagination only if that NSD exists
        if(nsd == null){
            return null;
        }

        // Set actual page number and total number of pages
        pageNum = pg.getPage(page);

        int size = pg.getNSDSize(nsd, nsdStruct);
        totPage = pg.getTotPage(size);

        int sizeVNFDep = nsdStruct.getSizeVNFDep();
        int sizeProperty = nsdStruct.getSizeProperty();
        int sizeVNF = nsdStruct.getSizeVNF();
        int sizeVNFFGD = nsdStruct.getSizeVNFFGD();
        int sizePNF = nsdStruct.getSizePNF();
        int sizeFlav = nsdStruct.getSizeFlav();
        int sizeCPS = nsdStruct.getSizeCPS();

        for(int i=(pageNum-1)*PAGE_SIZE, j=0; i<size && j<PAGE_SIZE; i++){
            if(i < sizeVNFDep){
                pageNSD.getVNFDependency().getGraph().add(nsd.getVNFDependency().getGraph().get(i));
                j++;
            }
            else if(i >= sizeVNFDep && i < sizeVNFDep+sizeProperty){
                pageNSD.getPropertyDefinition().getProperty().add(nsd.getPropertyDefinition().getProperty().get(i-sizeVNFDep));
                j++;
            }
            else if(i >= sizeVNFDep+sizeProperty && i < sizeVNFDep+sizeProperty+sizeVNF){
                pageNSD.getVNF().getVNFD().add(nsd.getVNF().getVNFD().get(i-sizeVNFDep-sizeProperty));
                j++;
            }
            else if(i >= sizeVNFDep+sizeProperty+sizeVNF && i < sizeVNFDep+sizeProperty+sizeVNF+sizeVNFFGD){
                pageNSD.getVNFFGD().getNetworkForwardingPaths().add(nsd.getVNFFGD().getNetworkForwardingPaths().get(i-sizeVNFDep-sizeProperty-sizeVNF));
                j++;
            }
            else if(i >= sizeVNFDep+sizeProperty+sizeVNF+sizeVNFFGD && i < sizeVNFDep+sizeProperty+sizeVNF+sizeVNFFGD+sizePNF){
                pageNSD.getPNF().getPNFD().add(nsd.getPNF().getPNFD().get(i-sizeVNFDep-sizeProperty-sizeVNF-sizeVNFFGD));
                j++;
            }
            else if(i >= sizeVNFDep+sizeProperty+sizeVNF+sizeVNFFGD+sizePNF && i < sizeVNFDep+sizeProperty+sizeVNF+sizeVNFFGD+sizePNF+sizeFlav){
                pageNSD.getFlavours().getServiceDeploymentFlavour().add(nsd.getFlavours().getServiceDeploymentFlavour().get(i-sizeVNFDep-sizeProperty-sizeVNF-sizeVNFFGD-sizePNF));
                j++;
            }
            else if(i >= sizeVNFDep+sizeProperty+sizeVNF+sizeVNFFGD+sizePNF+sizeFlav && i < sizeVNFDep+sizeProperty+sizeVNF+sizeVNFFGD+sizePNF+sizeFlav+sizeCPS){
                pageNSD.getConnectionPoints().getConnectionPoint().add(nsd.getConnectionPoints().getConnectionPoint().get(i-sizeVNFDep-sizeProperty-sizeVNF-sizeVNFFGD-sizePNF-sizeFlav));
                j++;
            }
        }

        pageNSD.setTotalPages(BigInteger.valueOf(totPage));
        pageNSD.setPage(BigInteger.valueOf(pageNum));

        next = pg.getNextPage(baseURI, path, pageNum, totPage);
        pageNSD.setNext(next.toString());

        return pageNSD;
    }

    /**
     * Add a NSD
     * @param nsd: the NSD to be added
     * @return The NSD or null if the NSD exists
     */
    public NSD addNSD(NSD nsd) {
        return nsDB.addNSD(nsd);
    }


    /**
     * Delete a NSD
     * @param nsdID: the ID of the NSD
     * @return The NSD or null if the NSD does not exist
     */
    public NSD deleteNSD(String nsdID) {
        return nsDB.deleteNSD(nsdID);
    }

    private NSD initNSD(String id){
        NSD nsd = new NSD();

        nsd.setId(id);
        nsd.setVendor(nsd.getVendor());
        nsd.setVersion(nsd.getVersion());
        nsd.setVNFDependency(new VNFDependency());
        nsd.setPropertyDefinition(new PropertyDefinition());
        nsd.setVNF(new VNF());
        nsd.setVNFFGD(new VNFFGD());
        nsd.setPNF(new PNF());
        nsd.setFlavours(new Flavours());
        nsd.setConnectionPoints(new ConnectionPoints());

        return nsd;
    }
}
