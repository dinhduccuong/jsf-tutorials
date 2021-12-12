package jmaster.io.bean;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "helloWorld", eager = false)
@ApplicationScoped
public class HelloWorld {

	public HelloWorld() {
		System.out.println("HelloWorld started!");
	}

	public String getMessage() {
		return "Hello World!";
	}
}
