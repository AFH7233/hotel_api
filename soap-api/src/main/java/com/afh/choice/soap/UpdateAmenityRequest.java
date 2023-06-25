//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.2 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2023.06.24 a las 10:28:46 PM CST 
//


package com.afh.choice.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="amenity" type="{http://afh.com/choice/soap}Amenity"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "amenity"
})
@XmlRootElement(name = "updateAmenityRequest")
public class UpdateAmenityRequest {

    @XmlElement(required = true)
    protected Amenity amenity;

    /**
     * Obtiene el valor de la propiedad amenity.
     * 
     * @return
     *     possible object is
     *     {@link Amenity }
     *     
     */
    public Amenity getAmenity() {
        return amenity;
    }

    /**
     * Define el valor de la propiedad amenity.
     * 
     * @param value
     *     allowed object is
     *     {@link Amenity }
     *     
     */
    public void setAmenity(Amenity value) {
        this.amenity = value;
    }

}
