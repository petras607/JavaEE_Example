package pieniazek.clnt;

import java.io.File;

public class PieniazekBean
{
	private String radios;
	private String fileName;
	private String image;
	private String modifiedFileName;
	private String wynik;
	
	public PieniazekBean()
	{
		radios = "";
		fileName = "";
		image = "";
		modifiedFileName = "";
		wynik = "";
	}
	
	public String getRadios() { return radios; }
	
	public void setRadios(String radios)
	{ 
		this.radios = radios;
		setWynik("");
	}
	
	public String getImage() { return image; }
	
	public void setImage(String image) { this.image = image; }
	
	public String getFileName() { return fileName; }
	
	public void setFileName(String fileName) { this.fileName = fileName; }
	
	public String getModifiedFileName() { return modifiedFileName; }
	
	public void setModifiedFileName(String modifiedFileName) { this.modifiedFileName = modifiedFileName; }

	public String getWynik()
	{ 
		return wynik; 
	}
	
	public void setWynik(String wynik)
	{
		if (fileName == null)
		{
			wynik = "Wybierz plik<br/>";
		}
		
		// Jeœli nie s¹ puste, wywo³ujemy web app
		if (radios == "")
		{
			wynik = "Nie wybrano akcji<br/>";
		}
		
		wynik = "";
		pieniazek.srvr.ImgProcProxy x = new pieniazek.srvr.ImgProcProxy();
		
		try
		{
			switch (radios)
			{
			case "negat":
				setModifiedFileName(x.startProc(fileName, 1));
				break;
			case "horiz":
				setModifiedFileName(x.startProc(fileName, 2));
				break;
			case "verti":
				setModifiedFileName(x.startProc(fileName, 3));
				break;
			case "oldify":
				setModifiedFileName(x.startProc(fileName, 4));
				break;
			}
		}
		catch (Exception e)
		{
			wynik = "Ups. Nie udalo sie. Chyba serwer nie dziala.";
			System.out.println(e.getMessage());
			//e.printStackTrace();
		}
		
		wynik = "";
	}
}
