package acsvrp.ui;

import java.io.File;
import javax.swing.filechooser.*;

public class VrpFilter extends FileFilter {

    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = Utils.getExtension(f);
        if (extension != null) {
            if (extension.equals("vrp")) {
                    return true;
            } else {
                return false;
            }
        }
        return false;
    }

    //The description of this filter
    public String getDescription() {
        return "Vehicle Routing Problem files";
    }
}

