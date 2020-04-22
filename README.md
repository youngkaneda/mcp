# parse-review

# O que é?
Parse-review é uma ferramenta desenvolvida na linguagem java com o propósito de analisar projetos desenvolvidos nesta mesma linguagem.
O principal objetivo da da análise é extrair informações do projeto buscando quebras de confinamento em classes que utilizam o framework JCF 
(Java Collection Framework). Posteriormente, será exibido ao usuário quais métodos causam a quebra do confinamento e alteram o estado do objeto.

# Um exemplo
Iremos tomar estas duas classes como exemplo :

```
// Target Class
class A{
    private List<A> elements;
    public List<A> getElements(){
        return this.elements;
    }
}
// Client Class
class C{
    private A a;
    public void m(){
        a.getElements().add(new A());
    }
}
```

`A` é a classe que possui o método que retorna um objeto pertencente ao framework JCF, o método `getElements()`, `C` é a classe que possui uma instância de `A` e realiza a chamada ao método `getElements`, permitindo que `C` tenha total acesso ao atributo de `A` e possa alterar o seu estado, o que é feito na chamada `a.getElements().add(new A())`, está nítido aqui que ocorreu a quebra do confinamento; e são estes os casos que nossa ferramenta procura identificar e detalhar, segue-se o resultado da análise do exemplo realizada pela ferramenta:

`<java.util.List, add[A], boolean, C, m[], void, null>`

# Como utilizar a ferramenta
1. Antes de tudo é preciso que você tenha instalado o maven, após instalado vá até a pasta deste projeto e abra o terminal, digite `mvn clean install` Isso instalará as dependências necessárias para que a ferramenta funcione.

2. Agora é necessário que você baixe ou clone um projeto do github na sua máquina, para clonar um projeto execute o seguinte comando dentro da pasta desejada: `git clone https://github.com/<perfil>/<repositorio>`.

3. Copie o caminho da raiz do projeto e passe como valor do argumento ```-d``` ou ```-dir``` no seguinte comando:
```
java -jar mcp --dir <caminho do projeto>

```
4. Após executar a classe, um arquivo `.txt` será gerado, e estará localizado no diretório desta ferramenta, com a seguinte saída:
```
<A,m(),java.util.List,C, m1(), void, mi()>
<...>
<...>
```
`A` - classe que possui o método `m()`;

`m()` - o método que possui como retorno algum dos tipos definidos no JCF, o retorno utlizado na formulação é meramente ilustrativo;

`java.util.List` - o tipo do retorno totalmente qualificado do método `m()`;

`C` - classe que possui o método `m1()`;

`m1()` - método que possui alguma invocação do método `m()`;

`void` - o tipo de retorno do método m1();

`mi` - o método invocado que causa a quebra do confinamento.
