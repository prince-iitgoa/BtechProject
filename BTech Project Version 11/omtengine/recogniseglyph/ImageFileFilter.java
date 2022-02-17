package recogniseglyph;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/*
 * This class implements a generic file name filter that allows the listing/selection
 * of JPEG/PNG files.
 */

public class ImageFileFilter extends FileFilter implements java.io.FileFilter
{
	public boolean accept(File f)
	{
		if (f.getName().toLowerCase().endsWith(".png")) return true;
		if (f.getName().toLowerCase().endsWith(".jpg")) return true;
		return false;
	}
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}
