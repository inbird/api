package hello.aaa.gapi;

import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

//@Controller
@Slf4j
//@RequestMapping("/gapi")
public class STT2 {

    //@GetMapping("stt-test2")
    public static void main(String[] args) {
        String filePath = "D:\\_GAPI\\stt_test2.wav";

        try {
            SpeechClient speech = SpeechClient.create(); // Client 생성
            System.out.printf("1111");

            // 오디오 파일에 대한 설정부분
            RecognitionConfig config = RecognitionConfig.newBuilder()
                    .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
                    .setSampleRateHertz(22050)
                    .setLanguageCode("en-US")
                    .build();
            System.out.printf("22222");
            RecognitionAudio audio = getRecognitionAudio(filePath); // Audio 파일에 대한 RecognitionAudio 인스턴스 생성
            RecognizeResponse response = speech.recognize(config, audio); // 요청에 대한 응답
            List<SpeechRecognitionResult> results = response.getResultsList(); // 응답 결과들
            System.out.printf("33333");
            for (SpeechRecognitionResult result: results) {
                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
                System.out.printf("Transcription: %s%n", alternative.getTranscript());
            }
            System.out.printf("44444");
            speech.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Local 이나 Remote이거나 구분해서 RecognitionAudio 만들어 주는 부분
    public static RecognitionAudio getRecognitionAudio(String filePath) throws IOException {
        RecognitionAudio recognitionAudio;

        // 파일이 GCS에 있는 경우
        if (filePath.startsWith("gs://")) {
            recognitionAudio = RecognitionAudio.newBuilder()
                    .setUri(filePath)
                    .build();

        }
        else { // 파일이 로컬에 있는 경우
            Path path = Paths.get(filePath);
            byte[] data = Files.readAllBytes(path);
            ByteString audioBytes = ByteString.copyFrom(data);
            recognitionAudio = RecognitionAudio.newBuilder()
                    .setContent(audioBytes)
                    .build();
            System.out.printf("0000000");
        }

        return recognitionAudio;
    }

}
