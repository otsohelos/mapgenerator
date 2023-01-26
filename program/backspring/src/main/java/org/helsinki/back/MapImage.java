package org.helsinki.back;

public class MapImage {

    public String getImageString() {
        String imageString = """
    <svg  xmlns='http://www.w3.org/2000/svg' version='1.2' baseProfile='tiny' width='100' height='100'>
        <circle cx='50' cy='50' r='25' stroke='teal' stroke-width='10' fill='aqua' />
     </svg> """;
        return  imageString;
    }
    
}
