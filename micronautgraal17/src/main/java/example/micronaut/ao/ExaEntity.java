package example.micronaut.ao;

public class ExaEntity {
    
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String doSom(String str){
        str = str.toLowerCase();
        return str;
    }
}
