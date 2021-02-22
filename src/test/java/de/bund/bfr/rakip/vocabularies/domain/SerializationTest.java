package de.bund.bfr.rakip.vocabularies.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

/** Sanity check of serializing each FskmlObject. */
public class SerializationTest {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Test
    public void testAccreditationProcedure() throws IOException {
        AccreditationProcedure procedure = new AccreditationProcedure(0, "mdstat", "name");
        String procedureString = MAPPER.writeValueAsString(procedure);
        MAPPER.readValue(procedureString, FskmlObject.class);
    }

    @Test
    public void testAvailability() throws IOException {
        Availability availability = new Availability(0, "name", "comment");
        String availabilityString = MAPPER.writeValueAsString(availability);
        MAPPER.readValue(availabilityString, FskmlObject.class);
    }

    @Test
    public void testBasicProcess() throws IOException {
        BasicProcess process = new BasicProcess(0, "name", new ModelClass(0, "Generic model"));
        String processString = MAPPER.writeValueAsString(process);
        MAPPER.readValue(processString, FskmlObject.class);
    }

    @Test
    public void testCollectionTool() throws IOException {
        CollectionTool tool = new CollectionTool(0, "name");
        String toolString = MAPPER.writeValueAsString(tool);
        MAPPER.readValue(toolString, FskmlObject.class);
    }

    @Test
    public void testCountry() throws IOException {
        Country country = new Country(0, "name", "iso");
        String countryString = MAPPER.writeValueAsString(country);
        MAPPER.readValue(countryString, FskmlObject.class);
    }

    @Test
    public void testFishArea() throws IOException {
        FishArea area = new FishArea(0, "name", "ssd");
        String areaString = MAPPER.writeValueAsString(area);
        MAPPER.readValue(areaString, FskmlObject.class);
    }

    @Test
    public void testHazard() throws IOException {
        Hazard hazard = new Hazard(0, "name", "ssd");
        String hazardString = MAPPER.writeValueAsString(hazard);
        MAPPER.readValue(hazardString, FskmlObject.class);
    }

    @Test
    public void testHazardType() throws IOException {
        HazardType type = new HazardType(0, "name");
        String typeString = MAPPER.writeValueAsString(type);
        MAPPER.readValue(typeString, FskmlObject.class);
    }

    @Test
    public void testIndSum() throws IOException {
        IndSum sum = new IndSum(0, "name", "ssd");
        String sumString = MAPPER.writeValueAsString(sum);
        MAPPER.readValue(sumString, FskmlObject.class);
    }

    @Test
    public void testLaboratoryAccreditation() throws IOException {
        LaboratoryAccreditation accreditation = new LaboratoryAccreditation(0, "name", "ssd");
        String accreditationString = MAPPER.writeValueAsString(accreditation);
        MAPPER.readValue(accreditationString, FskmlObject.class);
    }

    @Test
    public void testLanguage() throws IOException {
        Language language = new Language(0, "code", "name");
        String languageString = MAPPER.writeValueAsString(language);
        MAPPER.readValue(languageString, FskmlObject.class);
    }

    @Test
    public void testLanguageWrittenIn() throws IOException {
        LanguageWrittenIn language = new LanguageWrittenIn(0, "name");
        String languageString = MAPPER.writeValueAsString(language);
        MAPPER.readValue(languageString, FskmlObject.class);
    }

    @Test
    public void testModelClass() throws IOException {
        ModelClass modelClass = new ModelClass(0, "name");
        String modelClassString = MAPPER.writeValueAsString(modelClass);
        MAPPER.readValue(modelClassString, FskmlObject.class);
    }

    @Test
    public void testModelSubclass() throws IOException {
        ModelSubclass subclass = new ModelSubclass(0, "name", new ModelClass(0, "Generic model"));
        String subclassString = MAPPER.writeValueAsString(subclass);
        MAPPER.readValue(subclassString, FskmlObject.class);
    }

    @Test
    public void testPackaging() throws IOException {
        Packaging packaging = new Packaging(0, "name", "ssd", "comment");
        String packagingString = MAPPER.writeValueAsString(packaging);
        MAPPER.readValue(packagingString, FskmlObject.class);
    }

    @Test
    public void testParameterClassification() throws IOException {
        ParameterClassification classification = new ParameterClassification(0, "name", "comment");
        String classificationString = MAPPER.writeValueAsString(classification);
        MAPPER.readValue(classificationString, FskmlObject.class);
    }

    @Test
    public void testParameterDatatype() throws IOException {
        ParameterDatatype type = new ParameterDatatype(0, "name", "comment", "representedAs");
        String typeString = MAPPER.writeValueAsString(type);
        MAPPER.readValue(typeString, FskmlObject.class);
    }

    @Test
    public void testParameterDistribution() throws IOException {
        ParameterDistribution distribution = new ParameterDistribution(0, "name", "comment");
        String distributionString = MAPPER.writeValueAsString(distribution);
        MAPPER.readValue(distributionString, FskmlObject.class);
    }

    @Test
    public void testParameterSource() throws IOException {
        ParameterSource source = new ParameterSource(0, "name");
        String sourceString = MAPPER.writeValueAsString(source);
        MAPPER.readValue(sourceString, FskmlObject.class);
    }

    @Test
    public void testParameterSubject() throws IOException {
        ParameterSubject subject = new ParameterSubject(0, "name");
        String subjectString = MAPPER.writeValueAsString(subject);
        MAPPER.readValue(subjectString, FskmlObject.class);
    }

    @Test
    public void testPopulation() throws IOException {
        Population population = new Population(0, "name", "foodon");
        String populationString = MAPPER.writeValueAsString(population);
        MAPPER.readValue(populationString, FskmlObject.class);
    }

    @Test
    public void testProductionMethod() throws IOException {
        ProductionMethod method = new ProductionMethod(0, "name", "ssd", "comment");
        String methodString = MAPPER.writeValueAsString(method);
        MAPPER.readValue(methodString, FskmlObject.class);
    }

    @Test
    public void testProductMatrix() throws IOException {
        ProductMatrix matrix = new ProductMatrix(0, "ssd", "name", "comment");
        String matrixString = MAPPER.writeValueAsString(matrix);
        MAPPER.readValue(matrixString, FskmlObject.class);
    }

    @Test
    public void testProductTreatment() throws IOException {
        ProductTreatment treatment = new ProductTreatment(0, "name", "ssd", "comment");
        String treatmentString = MAPPER.writeValueAsString(treatment);
        MAPPER.readValue(treatmentString, FskmlObject.class);
    }

    @Test
    public void testPublicationStatus() throws IOException {
        PublicationStatus status = new PublicationStatus(0, "name", "comment");
        String statusString = MAPPER.writeValueAsString(status);
        MAPPER.readValue(statusString, FskmlObject.class);
    }

    @Test
    public void testPublicationType() throws IOException {
        PublicationType type = new PublicationType(0, "shortName", "fullName");
        String typeString = MAPPER.writeValueAsString(type);
        MAPPER.readValue(typeString, FskmlObject.class);
    }

    @Test
    public void testRegion() throws IOException {
        Region region = new Region(0, "name", "ssd");
        String regionString = MAPPER.writeValueAsString(region);
        MAPPER.readValue(regionString, FskmlObject.class);
    }

    @Test
    public void testRight() throws IOException {
        Right right = new Right(0, "shortname", "fullname", "url");
        String rightString = MAPPER.writeValueAsString(right);
        MAPPER.readValue(rightString, FskmlObject.class);
    }

    @Test
    public void testSamplingMethod() throws IOException {
        SamplingMethod method = new SamplingMethod(0, "name", "sampmd", "comment");
        String methodString = MAPPER.writeValueAsString(method);
        MAPPER.readValue(methodString, FskmlObject.class);
    }

    @Test
    public void testSamplingPoint() throws IOException {
        SamplingPoint point = new SamplingPoint(0, "name", "sampnt");
        String pointString = MAPPER.writeValueAsString(point);
        MAPPER.readValue(pointString, FskmlObject.class);
    }

    @Test
    public void testSamplingProgram() throws IOException {
        SamplingProgram program = new SamplingProgram(0, "name", "progType");
        String programString = MAPPER.writeValueAsString(program);
        MAPPER.readValue(programString, FskmlObject.class);
    }

    @Test
    public void testSamplingStrategy() throws IOException {
        SamplingStrategy strategy = new SamplingStrategy(0, "name", "comment");
        String strategyString = MAPPER.writeValueAsString(strategy);
        MAPPER.readValue(strategyString, FskmlObject.class);
    }

    @Test
    public void testSoftware() throws IOException {
        Software software = new Software(0, "name");
        String softwareString = MAPPER.writeValueAsString(software);
        MAPPER.readValue(softwareString, FskmlObject.class);
    }

    @Test
    public void testSource() throws IOException {
        Source source = new Source(1, "Name", "Comment");
        String sourceString = MAPPER.writeValueAsString(source);
        MAPPER.readValue(sourceString, FskmlObject.class);
    }

    @Test
    public void testStatus() throws IOException {
        Status status = new Status(0, "name", "comment");
        String statusString = MAPPER.writeValueAsString(status);
        MAPPER.readValue(statusString, FskmlObject.class);
    }

    @Test
    public void testTechnologyType() throws IOException {
        TechnologyType type = new TechnologyType(0, "ssd", "name", "comment");
        String typeString = MAPPER.writeValueAsString(type);
        MAPPER.readValue(typeString, FskmlObject.class);
    }

    @Test
    public void testUnit() throws IOException {
        Unit unit = new Unit(0, "name", "ssd", "comment", new UnitCategory(0, "name"));
        String unitString = MAPPER.writeValueAsString(unit);
        MAPPER.readValue(unitString, FskmlObject.class);
    }

    @Test
    public void testUnitCategory() throws IOException {
        UnitCategory category = new UnitCategory(0, "name");
        String categoryString = MAPPER.writeValueAsString(category);
        MAPPER.readValue(categoryString, FskmlObject.class);
    }
}
