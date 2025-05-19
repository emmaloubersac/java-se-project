/**
 * 
 */
/**
 * 
 */
module ELBank {
	requires java.xml;
	requires com.fasterxml.jackson.annotation;
	requires com.fasterxml.jackson.databind;
	opens components.Flow to com.fasterxml.jackson.databind;
	
}