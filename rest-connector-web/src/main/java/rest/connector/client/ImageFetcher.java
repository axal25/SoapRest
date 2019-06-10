package rest.connector.client;

import model.rest.MessageCollection;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

public class ImageFetcher {
    private final static String className = "ImageFetcher";

    public ImageFetcher() {}

//    public static Image getImage(String imageName ) {
//        URL url = getUrl( imageName );
//        InputStream inputStream = getInputStream( url );
//        Image image = getImage( inputStream );
//        return image;
//    }

    public URL getUrl( String imageName ) {
        final String functionName = "getUrl( String imageName )";
        URL anUrl = null;
        try {
            anUrl = this.getClass().getClassLoader().getResource("images" + File.separator + imageName);
            if( anUrl == null ) {
                throw new Exception("SingletonSessionBean >>> getUrl: anUrl == null");
            }
        }
        catch( Exception eResource ) {
            String response = MessageCollection.getException( className, functionName, eResource );
            System.out.println( response );
            return anUrl;
        }
        String urlContent = null;
        try {
            urlContent = anUrl.getContent().toString();
        }
        catch( Exception e ) {
            System.out.println( "anUrl.content: " + urlContent );
            String response = MessageCollection.getException( className, functionName, e );
            System.out.println( response );
        }
        return anUrl;
    }

//    public static InputStream getInputStream( URL anUrl ) {
//        final String functionName = "getInputStream( URL anUrl )";
//        InputStream inputStream = null;
//        try {
//            inputStream = anUrl.openStream();
//            if( inputStream == null ) {
//                throw new Exception("InputStream inputStream == null");
//            }
//            return inputStream;
//        }
//        catch( Exception eOpenStream ) {
//            String response = MessageCollection.getException( className, functionName, eOpenStream );
//            System.out.println( response );
//            return null;
//        }
//    }

//    public static java.awt.Image getImage( InputStream inputStream ) {
//        final String functionName = "getImage( InputStream inputStream )";
//        java.awt.Image image = null;
//
//        try {
//            image = ImageIO.read(inputStream);
//            if( image == null ) {
//                throw new Exception("Image image == null");
//            }
//            inputStream.close();
//            return image;
//        }
//        catch( Exception eOpenStream ) {
//            String response = MessageCollection.getException( className, functionName, eOpenStream );
//            System.out.println( response );
//            return null;
//        }
//    }

    // source: https://stackoverflow.com/questions/2295221/java-net-url-read-stream-to-byte
    public static ByteArrayOutputStream getImageUrlToBytes( URL imageUrl ) {
        final String functionName = "getImage( InputStream inputStream )";
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            inputStream = imageUrl.openStream();
            byte[] bytesPart = new byte[1024];
            int i = -1;
            byteArrayOutputStream = new ByteArrayOutputStream();
            while( (i = inputStream.read(bytesPart)) > 0 ) {
                byteArrayOutputStream.write(bytesPart, 0, i);
            }

        }
        catch( Exception e ) {
            String response = MessageCollection.getException( className, functionName, e );
            System.out.println( response );
            return null;
        }
        finally {
            if( inputStream != null ) {
                try {
                    inputStream.close();
                }
                catch ( Exception e ) {
                    String response = MessageCollection.getException( className, functionName, e );
                    System.out.println( response );
                    return null;
                }
            }
            return byteArrayOutputStream;
        }
    }

    public static byte[] getImageNameToBytes( String imageName ) throws Exception {
        final String functionName = "getImageNameToBytes( String imageName )";
        ImageFetcher imageFetcher = new ImageFetcher();
        URL url = imageFetcher.getUrl( imageName );
        ByteArrayOutputStream byteArrayOutputStream = getImageUrlToBytes( url );
        byte[] bytes = byteArrayOutputStream.toByteArray();
        if( bytes == null ) throw new Exception( className + " >>> " + functionName + ": Returning bytes == null" );
        return bytes;
    }

//    public static byte[] getImageToBytes( Image image ) {
//        BufferedImage bufferedImage = getImageToBufferedImage( image );
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ImageIO.write(bufferedImage, "extension", baos);
//        byte[] bytes = baos.toByteArray();
//        return bytes;
//    }
//
//    public static BufferedImage getImageToBufferedImage( Image image ) {
//        BufferedImage bufferedImage = new BufferedImage(
//                image.getWidth(null),
//                image.getHeight(null),
//                BufferedImage.TYPE_INT_RGB
//        );
//        Graphics graphics = bufferedImage.getGraphics();
//        graphics.drawImage(image, 0, 0, null);
//        graphics.dispose();
//        return bufferedImage;
//    }

}
