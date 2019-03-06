# data-format
The representation of all the most relevant information in the NFV and SDN contexts.

# Implementation

We started from *VerifOO & Verigraph* NFV schema. First of all, we studied its model and compared it with the most successful standard: **ETSI** and **TOSCA**. After summarized the major difference between ETSI and VerifOO/Verigraph, we started to modify their structure in order to be as closer as possible to the standard.

A resume schema is available [here](https://raw.githubusercontent.com/NFV-Architecture/data-format/master/doc/VerifOO-Verigraph_resume.png).

At this point we proposed our model.

### The Model

**Network Function Vitalization** (NFV) is an entity containing two main blocks:

- The Physical Network Infrastructure (PNI)
- The list of the Network Services offered by the network (NS) 

This implementation is not described in the standard, it cares only about the Network Services without infer anything about the physical structure that will host them. 

Here, is proposed a different structure with the respect to the standard, because we think that it is worth to store those additional information (PNIs) to manage the allocation of the virtual functions in the physical machines and retrieve them in future.

Neither in the **ETSI** standard and **TOSCA** does exist a definition for the NFV. They both start from the NSD entity.

Since PNI is necessary to *VerifOO & Verigraph* has been decided to left the root element (NFV), like in the previous schema, with, in addition to the physical structure, has been defined a new structure containing the Network Services Descriptors (NSD).

The previous schema was composed by both PNI and NSD without a clear difference between them, in terms of attribute and functionality. 

To have an overview of the whole schema see [this](https://raw.githubusercontent.com/NFV-Architecture/data-format/master/doc/NFV_model_final.png).

Full documentation [here](https://github.com/NFV-Architecture/data-format/blob/master/doc/DP2_NFV_Data_Models.pdf).
