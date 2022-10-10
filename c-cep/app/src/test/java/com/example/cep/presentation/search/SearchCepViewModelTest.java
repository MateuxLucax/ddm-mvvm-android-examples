package com.example.cep.presentation.search;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.view.View;

import com.example.cep.data.common.util.Result;
import com.example.cep.domain.model.Cep;
import com.example.cep.domain.usecase.search.SearchCepUseCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class SearchCepViewModelTest {

    private SearchCepViewModel viewModel;

    @Mock
    private SearchCepUseCase useCase;

    @Mock
    private View view;

    private AutoCloseable closeable;

    private Cep testCep;

    @Before
    public void setUp() {
        this.testCep = new Cep()
                .setCep("12345-678")
                .setBairro("Bairro teste")
                .setLogradouro("Logradour teste")
                .setLocalidade("Cidade teste")
                .setUf("UF teste");

        closeable = MockitoAnnotations.openMocks(this);
        viewModel = new SearchCepViewModel().setUseCase(useCase);
    }

    @After
    public void tearDown() throws Exception {
        closeable.close();
        useCase = null;
    }

    @Test
    public void searchCepShouldSetCorrectFieldsOnViewModelWhenCallSucceeds() {
        // Arrange
        Mockito.when(useCase.execute(Mockito.eq(testCep.getCep()))).thenReturn(new Result<>(testCep));

        // Act
        viewModel.setCepDigitado(testCep.getCep());
        viewModel.searchCep(view);

        // Assert
        assertEquals(viewModel.getCep(), testCep.getCep());
        assertEquals(viewModel.getBairro(), testCep.getBairro());
        assertEquals(viewModel.getLogradouro(), testCep.getLogradouro());
        assertEquals(viewModel.getCidade(), (testCep.getLocalidade() + " - " + testCep.getUf()));
        Mockito.verify(useCase, Mockito.times(1).description("was called exactly one time")).execute(testCep.getCep());
    }

    @Test
    public void searchCepShouldReturnErrorWhenCallFailsOnApiLevel() {
        // Arrange
        testCep.setErro(true);
        Mockito.when(useCase.execute(Mockito.eq(testCep.getCep()))).thenReturn(new Result<>(testCep));

        // Act
        viewModel.setCepDigitado(testCep.getCep());
        viewModel.searchCep(view);

        // Assert
        assertEquals(viewModel.getErro(), "CEP inválido");
        Mockito.verify(useCase, Mockito.times(1).description("was called exactly one time")).execute(testCep.getCep());
    }


    @Test
    public void searchCepShouldReturnErrorWhenCallFailsOnRequestLevel() {
        // Arrange
        Mockito.when(useCase.execute(Mockito.eq(testCep.getCep()))).thenReturn(new Result<>(new Exception("Request failed")));

        // Act
        viewModel.setCepDigitado(testCep.getCep());
        viewModel.searchCep(view);

        // Assert
        assertEquals(viewModel.getErro(), "Error: (Request failed)");
        Mockito.verify(useCase, Mockito.times(1).description("was called exactly one time")).execute(testCep.getCep());
    }

    @Test
    public void correctFormatTypedCepShouldReturnTrueOnValidation() {
        // Arrange
        String typedCep = "12345-67";
        viewModel.setCepDigitado(typedCep);

        // Act
        boolean result = viewModel.getFormatoCepValido();

        // Arrange
        assertTrue(result);
    }

    @Test
    public void wrongFormatTypedCepShouldReturnFalseOnValidation() {
        // Arrange
        String typedCep = "12345-6";
        viewModel.setCepDigitado(typedCep);

        // Act
        boolean result = viewModel.getFormatoCepValido();

        // Arrange
        assertFalse(result);
    }
}