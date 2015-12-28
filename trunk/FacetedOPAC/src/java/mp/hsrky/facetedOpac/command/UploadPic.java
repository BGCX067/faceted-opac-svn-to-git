package mp.hsrky.facetedOpac.command;

import java.io.*;
import java.util.Hashtable;
import javazoom.upload.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Vector;

/**
 * This servlet uses UploadBean to upload a file to a folder.
 * Default folder is $WEBAPP_HOME/WEB-INF/
 */
public class UploadPic extends HttpServlet {

    private String FOLDER = null;
    private boolean uploadSuccess;
    private String bookname;
    private String newFileName;

    public String getNewFileName() {
        return newFileName;
    }

    public void setNewFileName(String newFileName) {
        this.newFileName = newFileName;
    }
    public void init(String path, String bookname) throws ServletException {
        this.FOLDER = path;
        this.bookname = bookname;
    }

    /**
     * Processes upload to a folder.
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean uploadSuccess = false;
        if (MultipartFormDataRequest.isMultipartFormData(request)) {

            System.out.print("Folder to upload:" + FOLDER);
            try {
                int uploadlimit = 1024 * 1024 * 1024; // 1GB
                Vector listeners = null; // No upload listeners
                String parser = MultipartFormDataRequest.COSPARSER; // Cos parser
                String encoding = "iso-8859-1";
                MultipartFormDataRequest mrequest = new MultipartFormDataRequest(request, listeners, uploadlimit, parser, encoding);
                //change name
                Hashtable files = mrequest.getFiles();
                if ((files != null) && (!files.isEmpty())) {
                    UploadFile file = (UploadFile) files.get("image");
                    if (file != null) {
                        System.out.print(file.getFileName());
                        //file.setFileName("xxxx.jpg");
                        System.out.print(file.getContentType());
                        newFileName = "";
                        if (file.getContentType().equals("image/gif")) {
                            newFileName = bookname + ".gif";
                        } else {
                            newFileName = bookname + ".jpg";
                        }
                        setNewFileName(newFileName);
                        file.setFileName(newFileName);
                        System.out.print(file.getFileName());
                    }
                }

                // UploadBean initialization (scope = request).
                UploadBean upBean = new UploadBean();
                // Select the folder to upload files (for database or Zip, see documentation)

                upBean.setFolderstore(FOLDER);
                // ... See UploadBean documentation for all options
                // ... such as whitelist, blacklist ...
                // Handle upload now.
                upBean.store(mrequest);

                //to do - fix photo upload to test server only
                try {
                    UploadBean upBeanWorkCopy = new UploadBean();
                    upBeanWorkCopy.setFolderstore("D:\\NetbeansProject\\faceted-opac\\FacetedOPAC\\web\\book_cover\\");
                    upBeanWorkCopy.store(mrequest);
                } catch (Exception e) {
                }
                //error = "Upload completed successully";
                setUploadSuccess(true);
            } catch (IOException ex) {
                setUploadSuccess(false);
            //error = "IO Error : " + ex.getMessage();
            } catch (UploadException ex) {
                setUploadSuccess(false);
            //error = "Upload Error : " + ex.getMessage();
            }
        } else {
            setUploadSuccess(false);
        //error = "Not multipart/form-data request";
        }
    }

    public boolean getUploadSuccess() {
        return uploadSuccess;
    }

    public void setUploadSuccess(boolean status) {
        this.uploadSuccess = status;
    }

    public void destroy() {
    }
}