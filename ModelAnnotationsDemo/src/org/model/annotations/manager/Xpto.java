package org.model.annotations.manager;

public class Xpto {
	public static void findPackageNamesStartingWith() {
		 Package[]  xpto =Package.getPackages();
		 
		 for(Package pkg : xpto) {
			 int ss;
			if (pkg.getImplementationVendor() == null)
				ss=2;
			else
				ss = pkg.getImplementationVendor().compareTo("Oracle Corporation");
			
			 if (ss != 0)
				 System.out.println(pkg.getName() + "--" + pkg.getImplementationVendor());
		 }
		 
		// List<String> xxx = Package.getPackages().stream()
		//	        .map(Package::getName)
	//		        //.filter(n -> n.startsWith(prefix))
		//	        .collect(toList());
		 
		 
		 String aaa="";
		 
	}

}
