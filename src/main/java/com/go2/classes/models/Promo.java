/*
 * Java bean class for entity table promo 
 * Created on 17 Jul 2017 ( Date ISO 2017-07-17 - Time 15:05:23 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */

package com.go2.classes.models;

import java.io.Serializable;

import java.util.Date;

/**
 * Entity bean for table "promo"
 * 
 * @author Telosys Tools Generator
 *
 */
public class Promo implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Long       id           ; // Primary Key

    private Date       createdTime  ;
    private String     promoImageFour ;
    private String     promoImageOne ;
    private String     promoImageThree ;
    private String     promoImageTwo ;
    private Long       centerFourId ;
    private Long       centerOneId  ;
    private Long       centerThreeId ;
    private Long       centerTwoId  ;

    /**
     * Default constructor
     */
    public Promo()
    {
        super();
    }
    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR THE PRIMARY KEY 
    //----------------------------------------------------------------------
    /**
     * Set the "id" field value
     * This field is mapped on the database column "id" ( type "BIGINT", NotNull : true ) 
     * @param id
     */
	public void setId( Long id )
    {
        this.id = id ;
    }
    /**
     * Get the "id" field value
     * This field is mapped on the database column "id" ( type "BIGINT", NotNull : true ) 
     * @return the field value
     */
	public Long getId()
    {
        return this.id;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR DATA FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : created_time ( DATETIME ) 
    /**
     * Set the "createdTime" field value
     * This field is mapped on the database column "created_time" ( type "DATETIME", NotNull : false ) 
     * @param createdTime
     */
    public void setCreatedTime( Date createdTime )
    {
        this.createdTime = createdTime;
    }
    /**
     * Get the "createdTime" field value
     * This field is mapped on the database column "created_time" ( type "DATETIME", NotNull : false ) 
     * @return the field value
     */
    public Date getCreatedTime()
    {
        return this.createdTime;
    }

    //--- DATABASE MAPPING : promo_image_four ( VARCHAR ) 
    /**
     * Set the "promoImageFour" field value
     * This field is mapped on the database column "promo_image_four" ( type "VARCHAR", NotNull : false ) 
     * @param promoImageFour
     */
    public void setPromoImageFour( String promoImageFour )
    {
        this.promoImageFour = promoImageFour;
    }
    /**
     * Get the "promoImageFour" field value
     * This field is mapped on the database column "promo_image_four" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getPromoImageFour()
    {
        return this.promoImageFour;
    }

    //--- DATABASE MAPPING : promo_image_one ( VARCHAR ) 
    /**
     * Set the "promoImageOne" field value
     * This field is mapped on the database column "promo_image_one" ( type "VARCHAR", NotNull : false ) 
     * @param promoImageOne
     */
    public void setPromoImageOne( String promoImageOne )
    {
        this.promoImageOne = promoImageOne;
    }
    /**
     * Get the "promoImageOne" field value
     * This field is mapped on the database column "promo_image_one" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getPromoImageOne()
    {
        return this.promoImageOne;
    }

    //--- DATABASE MAPPING : promo_image_three ( VARCHAR ) 
    /**
     * Set the "promoImageThree" field value
     * This field is mapped on the database column "promo_image_three" ( type "VARCHAR", NotNull : false ) 
     * @param promoImageThree
     */
    public void setPromoImageThree( String promoImageThree )
    {
        this.promoImageThree = promoImageThree;
    }
    /**
     * Get the "promoImageThree" field value
     * This field is mapped on the database column "promo_image_three" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getPromoImageThree()
    {
        return this.promoImageThree;
    }

    //--- DATABASE MAPPING : promo_image_two ( VARCHAR ) 
    /**
     * Set the "promoImageTwo" field value
     * This field is mapped on the database column "promo_image_two" ( type "VARCHAR", NotNull : false ) 
     * @param promoImageTwo
     */
    public void setPromoImageTwo( String promoImageTwo )
    {
        this.promoImageTwo = promoImageTwo;
    }
    /**
     * Get the "promoImageTwo" field value
     * This field is mapped on the database column "promo_image_two" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getPromoImageTwo()
    {
        return this.promoImageTwo;
    }

    //--- DATABASE MAPPING : center_four_id ( BIGINT ) 
    /**
     * Set the "centerFourId" field value
     * This field is mapped on the database column "center_four_id" ( type "BIGINT", NotNull : false ) 
     * @param centerFourId
     */
    public void setCenterFourId( Long centerFourId )
    {
        this.centerFourId = centerFourId;
    }
    /**
     * Get the "centerFourId" field value
     * This field is mapped on the database column "center_four_id" ( type "BIGINT", NotNull : false ) 
     * @return the field value
     */
    public Long getCenterFourId()
    {
        return this.centerFourId;
    }

    //--- DATABASE MAPPING : center_one_id ( BIGINT ) 
    /**
     * Set the "centerOneId" field value
     * This field is mapped on the database column "center_one_id" ( type "BIGINT", NotNull : false ) 
     * @param centerOneId
     */
    public void setCenterOneId( Long centerOneId )
    {
        this.centerOneId = centerOneId;
    }
    /**
     * Get the "centerOneId" field value
     * This field is mapped on the database column "center_one_id" ( type "BIGINT", NotNull : false ) 
     * @return the field value
     */
    public Long getCenterOneId()
    {
        return this.centerOneId;
    }

    //--- DATABASE MAPPING : center_three_id ( BIGINT ) 
    /**
     * Set the "centerThreeId" field value
     * This field is mapped on the database column "center_three_id" ( type "BIGINT", NotNull : false ) 
     * @param centerThreeId
     */
    public void setCenterThreeId( Long centerThreeId )
    {
        this.centerThreeId = centerThreeId;
    }
    /**
     * Get the "centerThreeId" field value
     * This field is mapped on the database column "center_three_id" ( type "BIGINT", NotNull : false ) 
     * @return the field value
     */
    public Long getCenterThreeId()
    {
        return this.centerThreeId;
    }

    //--- DATABASE MAPPING : center_two_id ( BIGINT ) 
    /**
     * Set the "centerTwoId" field value
     * This field is mapped on the database column "center_two_id" ( type "BIGINT", NotNull : false ) 
     * @param centerTwoId
     */
    public void setCenterTwoId( Long centerTwoId )
    {
        this.centerTwoId = centerTwoId;
    }
    /**
     * Get the "centerTwoId" field value
     * This field is mapped on the database column "center_two_id" ( type "BIGINT", NotNull : false ) 
     * @return the field value
     */
    public Long getCenterTwoId()
    {
        return this.centerTwoId;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(createdTime);
        sb.append("|");
        sb.append(promoImageFour);
        sb.append("|");
        sb.append(promoImageOne);
        sb.append("|");
        sb.append(promoImageThree);
        sb.append("|");
        sb.append(promoImageTwo);
        sb.append("|");
        sb.append(centerFourId);
        sb.append("|");
        sb.append(centerOneId);
        sb.append("|");
        sb.append(centerThreeId);
        sb.append("|");
        sb.append(centerTwoId);
        return sb.toString(); 
    } 


}