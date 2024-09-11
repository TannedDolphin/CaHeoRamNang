package cayxanh.GreencareTest.controller;

import cayxanh.GreencareTest.entity.DichVu;
import cayxanh.GreencareTest.service.DichVuService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    public void testGetAllDichVu() {
        List<DichVu> dichVuList = Arrays.asList(new DichVu(), new DichVu());
        when(dichVuService.getAllDichVu()).thenReturn(dichVuList);

        ResponseEntity<List<DichVu>> response = dichVuController.getAllDichVu();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dichVuList, response.getBody());
    }

    @Test
    public void testGetDichVuById() {
        DichVu dichVu = new DichVu();
        when(dichVuService.getDichVuById(1)).thenReturn(dichVu);

        ResponseEntity<DichVu> response = dichVuController.getDichVuById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dichVu, response.getBody());
    }

    @Test
    public void testFindByName() {
        DichVu dichVu = new DichVu();
        when(dichVuService.findByName("TestDichVu")).thenReturn(dichVu);

        ResponseEntity<DichVu> response = dichVuController.findByName("TestDichVu");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dichVu, response.getBody());
    }

    @Test
    public void testCreateDichVu() {
        DichVu dichVu = new DichVu();
        when(dichVuService.createdichvu(dichVu)).thenReturn(dichVu);

        ResponseEntity<DichVu> response = dichVuController.createDichVu(dichVu);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(dichVu, response.getBody());
    }

    @Test
    public void testUpdateDichVu() {
        DichVu dichVu = new DichVu();
        dichVu.setDichvuid(1);
        when(dichVuService.updateDichvu(1, dichVu)).thenReturn(dichVu);

        ResponseEntity<DichVu> response = dichVuController.updateDichVu(1, dichVu);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dichVu, response.getBody());
    }

    @Test
    public void testDeleteDichVu() {
        doAnswer(invocation -> {
            // You can add any additional logic here if needed
            return null;
        }).when(dichVuService).deleteDichvu(1);

        ResponseEntity<Void> response = dichVuController.deleteDichVu(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(dichVuService, times(1)).deleteDichvu(1);
    }
}
