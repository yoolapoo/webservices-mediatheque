
package music;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="music" type="{http://spring.io/guides/gs-producing-web-service}music"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "music"
})
@XmlRootElement(name = "getMusicResponse", namespace = "http://spring.io/guides/gs-producing-web-service")
public class GetMusicResponse {

    @XmlElement(required = true)
    protected Music music;

    /**
     * Obtient la valeur de la propriété music.
     * 
     * @return
     *     possible object is
     *     {@link Music }
     *     
     */
    public Music getMusic() {
        return music;
    }

    /**
     * Définit la valeur de la propriété music.
     * 
     * @param value
     *     allowed object is
     *     {@link Music }
     *     
     */
    public void setMusic(Music value) {
        this.music = value;
    }

}
