package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.entity.DichVu;
import cayxanh.GreencareTest.repo.DichVuRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DichVuServiceTest {

    @Mock
    private DichVuRepo dichVuRepo;

    @InjectMocks
    private DichVuService dichVuService;

    private DichVu dichVu;

    @BeforeEach
    void setUp() {
        dichVu = new DichVu();
        dichVu.setDichvuid(1);
        dichVu.setDichvuname("Test Service");
        dichVu.setDichvuprice(100);
        dichVu.setDichvudescription("Test Description");

    }

    @Test
    void testGetDichVuById() {
        when(dichVuRepo.findById(1)).thenReturn(Optional.of(dichVu));
        DichVu found = dichVuService.getDichVuById(1);
        assertEquals(dichVu, found);
    }

    @Test
    void testGetAllDichVu() {
        List<DichVu> dichVuList = Arrays.asList(dichVu);
        when(dichVuRepo.findAll()).thenReturn(dichVuList);
        List<DichVu> foundList = dichVuService.getAllDichVu();
        assertEquals(dichVuList, foundList);
    }

    @Test
    void testFindByName() {
        when(dichVuRepo.findByName("Test Service")).thenReturn(Optional.of(dichVu));
        DichVu found = dichVuService.findByName("Test Service");
        assertEquals(dichVu, found);
    }

    @Test
    void testCreateDichVu() {
        when(dichVuRepo.findByName(dichVu.getDichvuname())).thenReturn(Optional.empty());
        when(dichVuRepo.save(dichVu)).thenReturn(dichVu);
        DichVu created = dichVuService.createdichvu(dichVu);
        assertEquals(dichVu, created);
    }

    @Test
    void testUpdateDichVu() {
        DichVu updatedDichVu = new DichVu();
        updatedDichVu.setDichvuname("New Test Service");
        updatedDichVu.setDichvuprice(200);
        updatedDichVu.setDichvudescription("New Test Description");

        when(dichVuRepo.findById(1)).thenReturn(Optional.of(dichVu));
        when(dichVuRepo.save(any(DichVu.class))).thenReturn(updatedDichVu);

        DichVu result = dichVuService.updateDichvu(1, updatedDichVu);
        assertNotNull(result);
        assertEquals("New Test Service", result.getDichvuname());
        assertEquals(200, result.getDichvuprice());
        assertEquals("New Test Description", result.getDichvudescription());

        verify(dichVuRepo, times(1)).findById(1);
        verify(dichVuRepo, times(1)).save(any(DichVu.class));
    }

    @Test
    void testDeleteDichVu() {
        when(dichVuRepo.findById(1)).thenReturn(Optional.of(dichVu));
        boolean isDeleted = dichVuService.deleteDichvu(1);
        assertTrue(isDeleted);
        verify(dichVuRepo, times(1)).deleteById(1);
    }
}
