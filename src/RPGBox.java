
public class RPGBox {
	private String box = "";
	private String header = "";
	private String topFull = "";
	private String bottomFull = "";
	private String oldHeader = "";		//Actually new Header
	private int longestLineLength;
	private final String HEADER_PRESET_START = "!===== [ ";
	private final String HEADER_PRESET_END = " ] =====!";
	private final String newLine = "\n";
	private String[] lines = new String[0];	

	/**
	 * Simple empty box with a title
	 * @param header
	 */
	public RPGBox(String header) {
		constructEmptyHeaderBox(header);
	}		

	/**
	 * Cunstructs Box with only a title
	 * @param header the title
	 */
	private void constructEmptyHeaderBox(String header) {
		this.header = header;
		box += "```diff\n";
		topFull += this.HEADER_PRESET_START;		
		topFull += header;
		topFull += this.HEADER_PRESET_END;	
		box += topFull;
		box += this.newLine;
		String temp = "";
		for(int i = 0; i < header.length(); i++) {
			temp += "=";
		}
		bottomFull += "!================"+temp+"!";
		box += bottomFull;
		box += "```";
	}

	/**
	 * Changes the header (title) of the Box
	 * @param header the new title
	 */
	public void setHeader(String header) {
		oldHeader = header;
		this.header = header;		
		refreshBox(header);
	}

	/**
	 * Gets the current header (title) 
	 * @return current header (title)
	 */
	public String getHeader() {
		return this.header;
	}

	/**
	 * Adds a string to the string array
	 * Automatically expands array if it is too small
	 * @param element
	 */
	public void addElement(String element) {
		if(lines.length == 0) {
			lines = new String[1];
			lines[0] = element;
		}else {
			boolean full = false;
			int tempInt = 0;

			for(int i = 0; i < lines.length; i++) {
				if(lines[i] != null) {
					tempInt++;
				}
			}

			if(tempInt == lines.length) {
				full = true;
			}

			if(!full) {
				for(int i = 0; i < lines.length; i++) {
					if(lines[i] != null) {
						lines[i] = element;
					}
				}
			}else {				
				String[] newLines = new String[lines.length+1];				
				for(int i = 0; i < lines.length; i++) {
					newLines[i] = lines[i];
				}
				newLines[newLines.length-1] = element;

				lines = newLines;
			}
		}
	}

	/**
	 * Printes the lines array
	 */
	public void printLines() {
		for(int i = 0; i < lines.length; i++) {
			System.out.println("Lines ["+i+"]: "+lines[i]);
		}
	}

	/**
	 * Refreshes the box and extends the size of the header/footer if the line is bigger
	 * @param line the new line to compare incase its bigger
	 */
	public void refreshBox(String line) {
		if(line.length()+2 > this.bottomFull.length() && oldHeader.length() == header.length()) {					
			box = "";
			topFull = "";
			bottomFull = "";
			this.longestLineLength = line.length() + 2;
			String temp = "";
			for(int i = 0; i < line.length(); i++) {
				temp += "=";
			}
			bottomFull += "!"+temp+"!";
			box += "```diff\n";		
			String temp2 = "!";
			for(int i = 0; i < (bottomFull.length()-header.length()-8)/2; i++) {
				temp2 += "=";
			}
			temp2 += " [ "+header+" ] ";
			for(int i = 0; i < (bottomFull.length()-header.length()-8)/2; i++) {
				temp2 += "=";
			}
			if((isOdd(header.length()) == false && isOdd(line.length()) == true) || (isOdd(header.length()) == true && isOdd(line.length()) == false) ) {				
				temp2 += "=";
			}
			temp2 += "!";
			topFull = temp2;	
			box += topFull;
			box += this.newLine;			
			box += bottomFull;
			box += "```";	
		}else {
			
		}
	}
	
	/**
	 * Adds a grey colored line in the box
	 * @param line the line to add
	 */
	public void addLine(String line) {	
		refreshBox(line);
		addElement("% "+line);
	}

	/**
	 * Determens if an int is odd
	 * @param n the int to check
	 * @return true if its odd, false if it isnt
	 */
	private boolean isOdd(int n) {
		if (n % 2 == 1) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Adds a green colored line in the box
	 * @param line the line to add
	 */
	public void addGreenLine(String line) {
		refreshBox(line);
		addElement("+ "+line);		
	}

	/**
	 * Adds a red colored line in the box
	 * @param line the Line
	 */
	public void addRedLine(String line) {
		refreshBox(line);
		addElement("- "+line);
	}	

	/**
	 * Returns the inner lines of the box
	 * @return inner lines of Box
	 */
	public String[] getLines() {
		return this.lines;
	}

	/**
	 * Returns the final Box, ready to print on discord
	 * @return the box in String format
	 */
	public String getBox() {
		String r = "```diff\n";
		r += this.topFull + "\n";		
		for(int i = 0; i < lines.length; i++) {
			r += lines[i] + "\n";
		}			
		r += this.bottomFull+"```";
		return r;		
	}
}
