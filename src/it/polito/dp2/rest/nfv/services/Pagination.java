package it.polito.dp2.rest.nfv.services;

import it.polito.dp2.rest.nfv.jaxb.NS;
import it.polito.dp2.rest.nfv.jaxb.NSD;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Pagination {
    private final int PAGE_SIZE = 5;
    private final String PAGE = "page";
    private ArrayList arrayID = null;
    private Logger logger = Logger.getLogger(Pagination.class.getName());

    /**
     * Get the page size
     * @return the page size
     */
    public int getPageSize(){
        return PAGE_SIZE;
    }

    /**
     * Set which is the actual page
     * @param page: the requested page
     * @return the actual page number
     */
    public int getPage(int page){
        int pageNum = 0;

        if(page < 1)
            pageNum = 1;
        else
            pageNum = page;

        return pageNum;
    }

    /**
     * Set the total number of pages base one the size of that specific element
     * @param size: number of elements owned by the considered item
     * @return the total number of pages
     */
    public int getTotPage(int size){
        return size/PAGE_SIZE + 1;
    }

    /**
     * Set the URI for the next page
     * @param baseURI: base URI for that element
     * @param path: related path for that element
     * @param pageNum: the actual page number
     * @param totPage: the total number of pages
     * @return the URI containing the link for the next page
     */
    public URI getNextPage(String baseURI, String path, int pageNum, int totPage){
        URI next = null;

        if(pageNum == totPage)
            next = UriBuilder.fromUri(baseURI).path(path).queryParam(PAGE, pageNum).build();
        else
            next = UriBuilder.fromUri(baseURI).path(path).queryParam(PAGE, pageNum+1).build();

        return next;
    }

    /**
     * Used only by NsServices.class: it will map the id of the NSD into an array list.
     * In that list at each index will correspond a string that is the real ID for that NSD.
     * @param ns: the NS for which is required the pagination
     * @return an ArrayList in which is mapped the IDs of different NSD (e.g. list[0] = "id1" and so on)
     */
    public ArrayList<String> mapNSDId(NS ns){
        arrayID = new ArrayList<String>();

        for(NSD nsd : ns.getNSD()){
            // In this way at each integer (from 0 to ns.getNSD().size()) will correspond the ID of that NSD
            arrayID.add(nsd.getId());
        }

        return arrayID;
    }

    /**
     * Given an (int)id it will get the real (String)id for that NSD
     * @param id: id of the considered NSD
     * @return the ID of the NSD
     * Note: Read mapNSDId() method
     */
    public String getNSDId(int id){
        if(arrayID == null){
            logger.log(Level.SEVERE, "Method 'mapNSDid()' must be called before use this method.");
            throw new InternalServerErrorException();
        }

        return (String)arrayID.get(id);
    }

    /**
     * Used only by NsServices.class: it allows to know how many elements are just printed before the NSD that im
     * considering. Every time the n-NSD is considered the index should start from 0, but the actual index is equal to
     * the total number of element printed before that page. So you need to re-map the index.
     * @param id: id of the considered NSD
     * @param sizeMap: HashMap such that: key -> id of the NSD, value: number of elements for that NSD
     * @return the offset to be subtracted to the index in order to obtain the right index for that NSD
     */
    public int getNSDOffset(int id, HashMap sizeMap){
        int tot = 0;

        for(int i=0; i<id; i++){
            tot += (int)sizeMap.get(i);
        }

        return tot;
    }

    /**
     * Get the size for that NSD
     * @param nsd: the nsd you want to know the size
     * @param nsdStruct: class NSDStruct that will be populated with the size information about the NSD.
     *                 It can be null
     * @return the calculated size for that NSD
     */
    public int getNSDSize(NSD nsd, NSDStruct nsdStruct){
        if(nsdStruct == null){
            int tot = 0;

            tot += nsd.getVNFDependency().getGraph().size();
            tot += nsd.getPropertyDefinition().getProperty().size();
            tot += nsd.getVNF().getVNFD().size();
            tot += nsd.getVNFFGD().getNetworkForwardingPaths().size();
            tot += nsd.getPNF().getPNFD().size();
            tot += nsd.getFlavours().getServiceDeploymentFlavour().size();
            tot += nsd.getConnectionPoints().getConnectionPoint().size();

            return tot;
        }
        else{
            nsdStruct.setSizeVNFDep(nsd.getVNFDependency().getGraph().size());
            nsdStruct.setSizeProperty(nsd.getPropertyDefinition().getProperty().size());
            nsdStruct.setSizeVNF(nsd.getVNF().getVNFD().size());
            nsdStruct.setSizeVNFFGD(nsd.getVNFFGD().getNetworkForwardingPaths().size());
            nsdStruct.setSizePNF(nsd.getPNF().getPNFD().size());
            nsdStruct.setSizeFlav(nsd.getFlavours().getServiceDeploymentFlavour().size());
            nsdStruct.setSizeCPS(nsd.getConnectionPoints().getConnectionPoint().size());

            return nsdStruct.getTotSize();
        }
    }
}

/**
 * NSDStruct class useful to manage the pagination in NsServices
 * It's only used inside NsServices.class
 */
class NSDStruct{
    private int sizeVNFDep;
    private int sizeProperty;
    private int sizeVNF;
    private int sizeVNFFGD;
    private int sizePNF;
    private int sizeFlav;
    private int sizeCPS;

    public NSDStruct(){
        this.sizeVNFDep = 0;
        this.sizeProperty = 0;
        this.sizeVNF = 0;
        this.sizeVNFFGD = 0;
        this.sizePNF = 0;
        this.sizeFlav = 0;
        this.sizeCPS = 0;
    }
    public void setSizeVNFDep(int sizeVNFDep) {
        this.sizeVNFDep = sizeVNFDep;
    }

    public void setSizeProperty(int sizeProperty) {
        this.sizeProperty = sizeProperty;
    }

    public void setSizeVNF(int sizeVNF) {
        this.sizeVNF = sizeVNF;
    }

    public void setSizeVNFFGD(int sizeVNFFGD) {
        this.sizeVNFFGD = sizeVNFFGD;
    }

    public void setSizePNF(int sizePNF) {
        this.sizePNF = sizePNF;
    }

    public void setSizeFlav(int sizeFlav) {
        this.sizeFlav = sizeFlav;
    }

    public void setSizeCPS(int sizeCPS) {
        this.sizeCPS = sizeCPS;
    }

    public int getSizeVNFDep() {
        return sizeVNFDep;
    }

    public int getSizeProperty() {
        return sizeProperty;
    }

    public int getSizeVNF() {
        return sizeVNF;
    }

    public int getSizeVNFFGD() {
        return sizeVNFFGD;
    }

    public int getSizePNF() {
        return sizePNF;
    }

    public int getSizeFlav() {
        return sizeFlav;
    }

    public int getSizeCPS() {
        return sizeCPS;
    }

    public int getTotSize(){
        return sizeVNFDep + sizeProperty + sizeVNF + sizeVNFFGD + sizePNF + sizeFlav + sizeCPS;
    }
}