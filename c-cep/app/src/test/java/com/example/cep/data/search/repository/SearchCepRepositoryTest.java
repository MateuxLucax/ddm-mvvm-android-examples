package com.example.cep.data.search.repository;

import static org.junit.Assert.*;

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
    }

    // TODO:
    @Test
    public void searchCepShouldReturnExceptionOnResultWhenCepRequestFails() {
        // Arrange
        // Act
        // Assert
    }

}