package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Category;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-04-03T08:41:38")
@StaticMetamodel(Code.class)
public class Code_ { 

    public static volatile SingularAttribute<Code, String> codeentry;
    public static volatile SingularAttribute<Code, String> codedescription;
    public static volatile SingularAttribute<Code, Integer> codeid;
    public static volatile SingularAttribute<Code, String> codename;
    public static volatile SingularAttribute<Code, Category> categories;

}