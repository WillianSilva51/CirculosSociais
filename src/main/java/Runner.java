import exceptions.CirculoNotFoundException;
import exceptions.ContatoNotFoundException;

public class Runner {

    private static final String AMIGOS = "amigos";
    private static final String TRABALHO = "trabalho";
    private static final String FAMILIA = "familia";
    private static final String JOAQUIM_EMAIL = "joaquim@ufc.br";
    private static final String JOAQUIM = "joaquim";
    private static final String ANA_EMAIL = "ana@ufc.br";
    private static final String ANA = "ana";
    private static final String MARIO_EMAIL = "mario@ufc.br";
    private static final String MARIO = "mario";
    private static final String JOSE_EMAIL = "jose@ufc.br";
    private static final String JOSE = "jose";
    private static final String JAMES_EMAIL = "james@ufc.com";
    private static final String JAMES = "james";

    public static void main(final String[] args) throws ContatoNotFoundException, CirculoNotFoundException {
        GContatos gcont = new GContatos();

        gcont.criarCirculo(FAMILIA, 3);
        gcont.criarCirculo(AMIGOS, 2);
        gcont.criarCirculo(TRABALHO, 3);
        System.out.println(gcont.getTodosCirculos()); //[amigos, familia, trabalho]

        gcont.criarContato(JAMES, JAMES_EMAIL);
        gcont.criarContato(MARIO, MARIO_EMAIL);
        gcont.criarContato(JOSE, JOSE_EMAIL);
        gcont.criarContato(ANA, ANA_EMAIL);
        gcont.criarContato(JOAQUIM, JOAQUIM_EMAIL);
        System.out.println(gcont.getTodosContatos()); //[ana, james, joaquim, jose, mario]

        gcont.adicionarContatoAoCirculo(MARIO, FAMILIA);
        System.out.println(gcont.recuperarCirculosDoContato(MARIO)); //[familia]

        gcont.adicionarContatoAoCirculo(JAMES, TRABALHO);
        gcont.adicionarContatoAoCirculo(JOAQUIM, TRABALHO);
        gcont.adicionarContatoAoCirculo(ANA, TRABALHO);
        System.out.println(gcont.recuperarContatosDoCirculo(TRABALHO)); //[ana, james, joaquim]

        gcont.adicionarContatoAoCirculo(JAMES, AMIGOS);
        gcont.adicionarContatoAoCirculo(MARIO, AMIGOS);
        System.out.println(gcont.recuperarContatosDoCirculo(AMIGOS)); //[james, mario]

        System.out.println(gcont.getCirculosEmComum(JAMES, ANA)); //[trabalho]
        System.out.println(gcont.getCirculosEmComum(JAMES, JOSE)); //[]
        System.out.println(gcont.getCirculosEmComum(JAMES, MARIO));//[amigos]
    }
}
