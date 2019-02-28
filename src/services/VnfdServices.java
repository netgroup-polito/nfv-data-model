package services;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import db.NsDB;
import jaxb.*;

import java.util.ArrayList;

//Defines the base URI for all resource URIs.
@ApplicationPath("/")
//The java class declares root resource and provider classes
public class VnfdServices extends Application {
    private NsDB nsDB = NsDB.getNsdDB();

    /**
     * Get all the VNFDs inside the NSD
     * @return A list of VNFD or null if there are not VNFD inside
     */


    /**
     * Get a VNFD inside the NSD
     * @return The VNFD or null if there are not VNFD inside
     */


    /**
     * Add a VNFD
     * @param vnfd
     * @return The added vnfd or null if the operation doesn't succeed
     */


    /**
     * Delete a VNFD from NSD
     * @param vnfdID
     * @return VNFD removed or null if the host is not present
     */

    /**
     * Modify a host from NSD
     * @param vnfd
     * @return Vnfd modified or null if the vnfd is not present
     */

    /**
     * Add a VirtualLink
     * @param vl
     * @return The added VirtualLink or null if the operation doesn't succeed
     */

    /**
     * Delete a VirtualLInk
     * @param vl
     * @return VirtualLink removed or null if the VirtualLink is not present
     */

    /**
     * Add a VDU
     * @param vnfdID
     * @param vdu
     * @return The added VDU or null if the operation doesn't succeed
     */

    /**
     * Delete a VDU
     * @param vnfdID
     * @param vdu
     * @return VDU removed or null if the VDU is not present
     */

    /**
     * Add a Dependency
     * @param vnfdID
     * @param relation
     * @return The added Dependency or null if the operation doesn't succeed
     */


    /**
     * Delete a Dependency
     * @param vnfdID
     * @param relation
     * @return Dependency removed or null if the Dependency is not present
     */

}