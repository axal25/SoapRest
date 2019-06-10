package rest.api.proto;

import model.rest.MessageCollection;

public class ProtoUtils {
    public static final String className = "ProtoUtils";

    public static ProtoStudent generateExampleProtoStudent() {
        String[] courses = new String[] {
                "ProtoCourse #1",
                "ProtoCourse #2",
                "ProtoCourse #3",
                "ProtoCourse #4",
                "ProtoCourse #5",
                "ProtoCourse #6",
                "ProtoCourse #7",
                "ProtoCourse #8",
                "ProtoCourse #9",
                "ProtoCourse #10"
        };
        return new ProtoStudent.Builder()
                .setName("ExampleProtoStudent")
                .setAge(119)
                .setCourses(courses)
                .build();
    }

    public static ProtoBuffImpl.MyProtoBuff getProtoBuffImpl_MyProtoBuff(ProtoStudent protoStudent ) {
        final ProtoBuffImpl.MyProtoBuff myProtoBuffMessage =
                ProtoBuffImpl.MyProtoBuff.newBuilder()
                .setClassName( protoStudent.className )
                .setName( protoStudent.getName() )
                .setAge( protoStudent.getAge() )
                .addAllCourse( protoStudent.getCoursesAsList() )
                .build();
        return myProtoBuffMessage;
    }

    public static byte[] getByteArray(ProtoBuffImpl.MyProtoBuff myProtoBuffMessage ) {
        final byte[] binaryMyProtoBuff = myProtoBuffMessage.toByteArray();
        return binaryMyProtoBuff;
    }

    public static ProtoStudent getProtoStudent(byte[] binaryMyProtoBuff ) {
        final String functionName = "getProtoStudent( byte[] binaryMyProtoBuff )";
        ProtoStudent protoStudent = null;
        try {
            final ProtoBuffImpl.MyProtoBuff myProtoBuffMessage = ProtoBuffImpl.MyProtoBuff.parseFrom( binaryMyProtoBuff );
            final java.util.List<String> courses = myProtoBuffMessage.getCourseList();
            protoStudent = new ProtoStudent.Builder()
                    .setName( myProtoBuffMessage.getName() )
                    .setAge( myProtoBuffMessage.getAge() )
                    .setCourses( courses )
                    .build();
        }
        catch( Exception e ) {
            System.err.println(MessageCollection.getException( className, functionName, e ));
        }
        return protoStudent;
    }
}
