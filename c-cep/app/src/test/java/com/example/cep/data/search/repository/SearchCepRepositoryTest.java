package com.example.cep.data.search.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.example.cep.data.common.util.Result;
import com.example.cep.data.search.datasource.ISearchDatasource;
import com.example.cep.domain.model.Cep;
import com.example.cep.domain.usecase.search.ISearchCepRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class SearchCepRepositoryTest {

    private ISearchCepRepository repository;

    @Mock
    private ISearchDatasource datasource;

    private AutoCloseable closeable;

    @Before
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        repository = new SearchCepRepository(datasource);
    }

    @After
    public void tearDown() throws Exception {
        closeable.close();
        datasource = null;
    }

    @Test
    public void searchCepShouldReturnCorrectCepBasedOnId() {
        // Arrange
        String testCep = "12345-678";
        Cep expectedCep = new Cep().setCep(testCep);
        Mockito.when(datasource.search(Mockito.eq(testCep))).thenReturn(new Result<>(expectedCep));

        // Act
        Result<Cep> result = repository.search(testCep);

        // Assert
        assertEquals(result.getResponse(), expectedCep);
        assertEquals(result.getResponse().getCep(), testCep);
        Mockito.verify(datasource, Mockito.times(1).description("was called exactly one time")).search(testCep);
    }

    @Test
    public void searchCepShouldRetrieveAlreadySearchedCepFromCache() {
        // Arrange
        String testCep = "12345-678";
        Cep expectedCep = new Cep().setCep(testCep);
        Mockito.when(datasource.search(Mockito.eq(testCep))).thenReturn(new Result<>(expectedCep));

        // Act
        Result<Cep> resultFromDatasource = repository.search(testCep);
        Result<Cep> resultFromCache = repository.search(testCep);

        // Assert
        assertEquals(resultFromDatasource.getResponse(), expectedCep);
        assertEquals(resultFromDatasource.getResponse().getCep(), testCep);
        assertEquals(resultFromCache.getResponse(), expectedCep);
        assertEquals(resultFromCache.getResponse().getCep(), testCep);
        assertEquals(resultFromDatasource, resultFromCache);
        assertEquals(resultFromDatasource, resultFromCache);
        Mockito.verify(datasource, Mockito.times(1).description("was called exactly one time")).search(testCep);
    }

    @Test
    public void searchCepShouldReturnExceptionOnResultWhenCepRequestFails() {
        // Arrange
        String testCep = "12345-678";
        Result<Cep> expectedResult = new Result<>(new Exception("Request failed"));
        Mockito.when(datasource.search(Mockito.eq(testCep))).thenReturn(expectedResult);

        // Act
        Result<Cep> result = repository.search(testCep);

        // Assert
        assertTrue(result.hasError());
        assertEquals(result, expectedResult);
        assertEquals(result.getError(), expectedResult.getError());
        Mockito.verify(datasource, Mockito.times(1).description("was called exactly one time")).search(testCep);
    }

}