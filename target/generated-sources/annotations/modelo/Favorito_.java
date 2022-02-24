package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.ElementoPatrimonial;
import modelo.FavoritoPK;
import modelo.Usuario;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-02-24T18:14:44")
@StaticMetamodel(Favorito.class)
public class Favorito_ { 

    public static volatile SingularAttribute<Favorito, ElementoPatrimonial> elementoPatrimonial;
    public static volatile SingularAttribute<Favorito, FavoritoPK> favoritoPK;
    public static volatile SingularAttribute<Favorito, Usuario> usuario;
    public static volatile SingularAttribute<Favorito, String> comentarios;

}