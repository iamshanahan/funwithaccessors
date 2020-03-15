package accessors.containingpackage;


import java.util.Date;

// private class PackagePrivateDefaultTestClass {  /* Does not compile */
// protected class PackagePrivateDefaultTestClass { /* Does not compile */
public class PackagePrivateDefaultTestClass {
    public int publicMember;
    protected int protectedMember;
    private int privateMember;
    int packagePrivateDefaultMember;

    public static int publicStaticMember;
    protected static int protectedStaticMember;
    private static int privateStaticMember;
    static int packagePrivateDefaultStaticMember;

    public class PublicSubclass {}
    protected class ProtectedSubclass{}
    private class PrivateSubclass{
        Date time;
        PrivateSubclass() {
            time = new Date();
        }
    }
    class PackagePrivateDefaultSubclass{}

    static public class PublicStaticSubclass {}
    static protected class ProtectedStaticSubclass{}
    static private class PrivateStaticSubclass{}
    static class PackagePrivateDefaultStaticSubclass{}


    /**
     * All accessor combos accessible from an instance method or CTOR
     */
    public void set() {
        publicMember = 17;
        privateMember = 17;
        protectedMember = 17;
        packagePrivateDefaultMember = 17;

        publicStaticMember = 17;
        privateStaticMember = 17;
        protectedStaticMember = 17;
        packagePrivateDefaultStaticMember = 17;

        new PublicSubclass();
        new ProtectedSubclass();
        new PrivateSubclass();
        new PackagePrivateDefaultSubclass();

        new PublicStaticSubclass();
        new ProtectedStaticSubclass();
        new PrivateStaticSubclass();
        new PackagePrivateDefaultStaticSubclass();
    }

    /**
     * Only static members accessible from a static function
     */
    public static void staticSet() {
        // publicMember = 17; /* Does not compile */
        // privateMember = 17; /* Does not compile */
        // protectedMember = 17; /* Does not compile */
        // packagePrivateDefaultMember = 17; /* Does not compile */

        publicStaticMember = 17;
        privateStaticMember = 17;
        protectedStaticMember = 17;
        packagePrivateDefaultStaticMember = 17;

        // new PublicSubclass(); /* Does not compile */
        // new ProtectedSubclass(); /* Does not compile */
        // new PrivateSubclass(); /* Does not compile */
        // new PackagePrivateDefaultSubclass(); /* Does not compile */

        new PublicStaticSubclass();
        new ProtectedStaticSubclass();
        new PrivateStaticSubclass();
        new PackagePrivateDefaultStaticSubclass();
    }
}
