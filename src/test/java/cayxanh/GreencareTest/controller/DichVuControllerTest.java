package cayxanh.GreencareTest.controller;

import cayxanh.GreencareTest.dto.request.CreateDichVuRequest;
import cayxanh.GreencareTest.entity.DichVu;
import cayxanh.GreencareTest.service.DichVuService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class DichVuControllerTest {

    @Mock
    private DichVuService dichVuService;

    @InjectMocks
    private DichVuController dichVuController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateDichVu() {
        CreateDichVuRequest request = new CreateDichVuRequest();
        DichVu dichVu = new DichVu();
        when(dichVuService.createDichVu(any(CreateDichVuRequest.class))).thenReturn(dichVu);

        ResponseEntity<DichVu> response = dichVuController.createDichVu(request);

        assertEquals(dichVu, response.getBody());
        verify(dichVuService, times(1)).createDichVu(any(CreateDichVuRequest.class));
    }

    @Test
    public void testUpdateDichVu() {
        CreateDichVuRequest request = new CreateDichVuRequest();
        DichVu dichVu = new DichVu();
        when(dichVuService.updateDichVu(anyInt(), any(CreateDichVuRequest.class))).thenReturn(dichVu);

        ResponseEntity<DichVu> response = dichVuController.updateDichVu(1, request);

        assertEquals(dichVu, response.getBody());
        verify(dichVuService, times(1)).updateDichVu(anyInt(), any(CreateDichVuRequest.class));
    }

    @Test
    public void testDeleteDichVu() {
        doNothing().when(dichVuService).deleteDichVu(anyInt());

        ResponseEntity<Void> response = dichVuController.deleteDichVu(1);

        assertEquals(204, response.getStatusCodeValue());
        verify(dichVuService, times(1)).deleteDichVu(anyInt());
    }

    @Test
    public void testGetAllDichVu() {
        List<DichVu> dichVus = Arrays.asList(new DichVu(), new DichVu());
        when(dichVuService.getAllDichVu()).thenReturn(dichVus);

        ResponseEntity<List<DichVu>> response = dichVuController.getAllDichVu();

        assertEquals(dichVus, response.getBody());
        verify(dichVuService, times(1)).getAllDichVu();
    }

    @Test
    public void testGetDichVuById() {
        DichVu dichVu = new DichVu();
        when(dichVuService.getDichVuById(anyInt())).thenReturn(dichVu);

        ResponseEntity<DichVu> response = dichVuController.getDichVuById(1);

        assertEquals(dichVu, response.getBody());
        verify(dichVuService, times(1)).getDichVuById(anyInt());
    }
}
