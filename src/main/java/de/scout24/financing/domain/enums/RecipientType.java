package de.scout24.financing.domain.enums;

public enum RecipientType {
    THREAD_INITIATOR,
    THREAD_FAVORITER,

    EXPERT_INTERESTED, //BASED ON HIS FILTERS

    EXPERT_INITIATOR,//EXPERT OF CURRENT ANSWER
    EXPERT_OTHERS,//ANY OTHER EXPERT IN THE THREAD

    COMMENT_INITIATOR,
    COMMENT_OTHERS,

    USER,

    USER_CONTACTER, //USER WHO ASKS TO CONTACT AN EXPERT
    EXPERT_CONTACT_RECEIVER, // EXPERT WHO GET ALL THE INFO OF

    SUPPORT_USER;

}