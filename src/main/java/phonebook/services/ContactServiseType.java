package phonebook.services;

public enum ContactServiseType {
    MEMORY("память компьютера"),
    CSV_FILE("CSV файл"),
    JSON_FILE("JSON файл"),
    XML_FILE("XML файл"),
    OBJECT_FILE("OBJECT файл"),
    NETWORK("удаленный сервер");

    private String repositoryType;

    ContactServiseType(String repositoryType) {
        this.repositoryType = repositoryType;
    }

    public String getRepositoryType() {
        return repositoryType;
    }
}
