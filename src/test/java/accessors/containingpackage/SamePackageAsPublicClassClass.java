package accessors.containingpackage;

import org.omg.CORBA.PUBLIC_MEMBER;

public class SamePackageAsPublicClassClass {

    public int SamePackageAsPublicClassClass( int j ) {
        return j+1;
    }
    public void set() {
        PublicTestClass.staticSet();
        PublicTestClass.ProtectedStaticSubclass foo = new PublicTestClass.ProtectedStaticSubclass();
        PublicTestClass ptc = new PublicTestClass();
        PublicTestClass.PublicStaticSubclass pssc = new PublicTestClass.PublicStaticSubclass();
        PublicTestClass.PackagePrivateDefaultStaticSubclass ppdssc = new PublicTestClass.PackagePrivateDefaultStaticSubclass();
        ptc.publicMember = SamePackageAsPublicClassClass(17 );
    }

    public void instantiateInnerClases( PublicTestClass ptc ) {
        // such weird syntax
        ptc.new PublicSubclass();
        ptc.new ProtectedSubclass();
        ptc.new PackagePrivateDefaultSubclass();
        // ptc.new PrivateSubclass(); /* Does not compile */

        new PublicTestClass.PublicStaticSubclass();
        new PublicTestClass.ProtectedStaticSubclass();
        new PublicTestClass.PackagePrivateDefaultStaticSubclass();
        // new PublicTestClass.PrivateStaticSubclass(); /* Does not compile */
    }
}
