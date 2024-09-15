package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.dto.request.CreateDichVuRequest;
import cayxanh.GreencareTest.entity.DichVu;
import cayxanh.GreencareTest.repo.DichVuRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DichVuServiceTest {

    @Mock
    private DichVuRepo dichVuRepo;

    @InjectMocks
    private DichVuService dichVuService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateDichVu() {
        CreateDichVuRequest request = new CreateDichVuRequest();
        request.setDichvuname("Cleaning");
        request.setDichvudescription("Cleaning service");

        DichVu dichVu = new DichVu();
        dichVu.setDichvuname(request.getDichvuname());
        dichVu.setDichvudescription(request.getDichvudescription());

        when(dichVuRepo.save(any(DichVu.class))).thenReturn(dichVu);

        DichVu createdDichVu = dichVuService.createDichVu(request);

        assertNotNull(createdDichVu);
        assertEquals("Cleaning", createdDichVu.getDichvuname());
        assertEquals("Cleaning service", createdDichVu.getDichvudescription());
    }

    @Test
    public void testUpdateDichVu_Success() {
        Integer dichVuId = 1;
        CreateDichVuRequest request = new CreateDichVuRequest();
        request.setDichvuname("Updated Cleaning");
        request.setDichvudescription("Updated cleaning service");

        DichVu existingDichVu = new DichVu();
        existingDichVu.setDichvuid(dichVuId);
        existingDichVu.setDichvuname("Cleaning");
        existingDichVu.setDichvudescription("Cleaning service");

        DichVu updatedDichVu = new DichVu();
        updatedDichVu.setDichvuid(dichVuId);
        updatedDichVu.setDichvuname(request.getDichvuname());
        updatedDichVu.setDichvudescription(request.getDichvudescription());

        when(dichVuRepo.findById(dichVuId)).thenReturn(Optional.of(existingDichVu));
        when(dichVuRepo.save(any(DichVu.class))).thenReturn(updatedDichVu);

        DichVu result = dichVuService.updateDichVu(dichVuId, request);

        assertNotNull(result);
        assertEquals("Updated Cleaning", result.getDichvuname());
        assertEquals("Updated cleaning service", result.getDichvudescription());
    }

    @Test
    public void testUpdateDichVu_Failure() {
        Integer dichVuId = 1;
        CreateDichVuRequest request = new CreateDichVuRequest();
        request.setDichvuname("Updated Cleaning");
        request.setDichvudescription("Updated cleaning service");

        when(dichVuRepo.findById(dichVuId)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            dichVuService.updateDichVu(dichVuId, request);
        });
        assertEquals("DichVu không tồn tại với ID: " + dichVuId, thrown.getMessage());
    }

    @Test
    public void testDeleteDichVu_Success() {
        Integer dichVuId = 1;

        when(dichVuRepo.existsById(dichVuId)).thenReturn(true);

        dichVuService.deleteDichVu(dichVuId);

        verify(dichVuRepo, times(1)).deleteById(dichVuId);
    }

    @Test
    public void testDeleteDichVu_Failure() {
        Integer dichVuId = 1;

        when(dichVuRepo.existsById(dichVuId)).thenReturn(false);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            dichVuService.deleteDichVu(dichVuId);
        });
        assertEquals("DichVu không tồn tại với ID: " + dichVuId, thrown.getMessage());
    }

    @Test
    public void testGetAllDichVu() {
        DichVu dichVu1 = new DichVu();
        dichVu1.setDichvuname("Cleaning");

        DichVu dichVu2 = new DichVu();
        dichVu2.setDichvuname("Gardening");

        when(dichVuRepo.findAll()).thenReturn(Arrays.asList(dichVu1, dichVu2));

        List<DichVu> dichVus = dichVuService.getAllDichVu();

        assertNotNull(dichVus);
        assertEquals(2, dichVus.size());
        assertEquals("Cleaning", dichVus.get(0).getDichvuname());
        assertEquals("Gardening", dichVus.get(1).getDichvuname());
    }

    @Test
    public void testGetDichVuById_Success() {
        Integer dichVuId = 1;

        DichVu dichVu = new DichVu();
        dichVu.setDichvuid(dichVuId);
        dichVu.setDichvuname("Cleaning");

        when(dichVuRepo.findById(dichVuId)).thenReturn(Optional.of(dichVu));

        DichVu result = dichVuService.getDichVuById(dichVuId);

        assertNotNull(result);
        assertEquals(dichVuId, result.getDichvuid());
        assertEquals("Cleaning", result.getDichvuname());
    }

    @Test
    public void testGetDichVuById_Failure() {
        Integer dichVuId = 1;

        when(dichVuRepo.findById(dichVuId)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            dichVuService.getDichVuById(dichVuId);
        });
        assertEquals("DichVu không tồn tại với ID: " + dichVuId, thrown.getMessage());
    }
}
