#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config;

import javax.annotation.PostConstruct;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.wadl.internal.WadlResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ${package}.rest.v1.HelloResource;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

@Component
public class JerseyConfig extends ResourceConfig {
	
	
	@Value("${symbol_dollar}{spring.jersey.application-path:/}")
	private String apiPath;


  public JerseyConfig() {
     // Register endpoints, providers, ...
     this.registerEndpoints();
   }
 
  private void registerEndpoints() {
     this.register(HelloResource.class);
     // Available at /<Jersey's servlet path>/application.wadl
     this.register(WadlResource.class);
   }
  
  @PostConstruct
   public void init() {
      // Register components where DI is needed
       this.configureSwagger();
     }
  
  //Configuring Swaager
  private void configureSwagger() {
		// Available at localhost:port/swagger.json
		this.register(ApiListingResource.class);
		this.register(SwaggerSerializers.class);

		BeanConfig config = new BeanConfig();
		config.setConfigId("${artifactId}-example");
		config.setTitle("Spring Boot + Jersey + Swagger + Docker Example");
		config.setVersion("v1");
		config.setContact("Jersey POC");
		config.setSchemes(new String[] { "http", "https" });
		config.setBasePath(this.apiPath);
		config.setResourcePackage("${package}.rest.v1");
		config.setPrettyPrint(true);
		config.setScan(true);
	}
  
}  