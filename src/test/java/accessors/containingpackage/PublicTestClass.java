package accessors.containingpackage;

public class PublicTestClass {
    public int publicMember;
    protected int protectedMember;
    private int privateMember;
    int packagePrivateDefaultMember;

    public static int publicStaticMember;
    protected static int protectedStaticMember;
    private static int privateStaticMember;
    static int packagePrivateDefaultStaticMember;

    public class PublicSubclass {}
    protected final class ProtectedSubclass{}
    private class PrivateSubclass{
        public int publicMemberInPrivateClass;
        private int privateMemberInPrivateClass;
    }
    class PackagePrivateDefaultSubclass{}

    static public class PublicStaticSubclass {}
    static protected class ProtectedStaticSubclass{}
    static private class PrivateStaticSubclass{
        public int publicMemberInPrivateClass;
        private int privateMemberInPrivateClass;
    }
    static class PackagePrivateDefaultStaticSubclass{}


    /**
     * All accessor combos available from an instance method or CTOR
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
        PrivateSubclass privatesubclass = new PrivateSubclass();
        privatesubclass.privateMemberInPrivateClass = 3;
        privatesubclass.publicMemberInPrivateClass = 4;
        new PackagePrivateDefaultSubclass();

        new PublicStaticSubclass();
        new ProtectedStaticSubclass();
        new PrivateStaticSubclass();
        new PackagePrivateDefaultStaticSubclass();
    }

    /**
     * Only static members available from a static function without an instance.
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
