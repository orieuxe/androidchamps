package android.eservices.pogchamps.data.di;

import android.content.Context;
import android.eservices.pogchamps.data.api.service.MatchService;
import android.eservices.pogchamps.data.api.service.ParticipantService;
import android.eservices.pogchamps.data.api.service.TournamentService;
import android.eservices.pogchamps.data.repository.match.IMatchRepository;
import android.eservices.pogchamps.data.repository.match.MatchRepository;
import android.eservices.pogchamps.data.repository.participant.IParticipantRepository;
import android.eservices.pogchamps.data.repository.participant.ParticipantRepository;
import android.eservices.pogchamps.data.repository.tournament.ITournamentRepository;
import android.eservices.pogchamps.data.repository.tournament.TournamentRepository;
import android.eservices.pogchamps.results.viewmodel.ViewModelFactory;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Please never do that in a production app. Ever.
 * For the purpose of our course, this is the best option to cover interesting topics as
 * we don't have time to dig into Dependency Injection frameworks such as the famous Dagger.
 * Singleton are compulsory for some classes, such as the one here. If you don't know why, then ask me.
 * Note that this god object doesn't handle Scopes nor component lifecycles so this really shouldn't be
 * the way to go when you master the craft of your software.
 */
public class FakeDependencyInjection {

    private static TournamentService tournamentService;
    private static ParticipantService participantService;
    private static MatchService matchService;
    private static Retrofit retrofit;
    private static Gson gson;
    private static ITournamentRepository tournamentRepository;
    private static IParticipantRepository participantRepository;
    private static IMatchRepository matchRepository;
    private static Context applicationContext;
    private static ViewModelFactory viewModelFactory;

    public static ViewModelFactory getViewModelFactory() {
        if (viewModelFactory == null) {
            viewModelFactory = new ViewModelFactory(getTournamentRepository(), getParticipantRepository(), getMatchRepository());
        }
        return viewModelFactory;
    }

    public static ITournamentRepository getTournamentRepository() {
        if (tournamentRepository == null) {
            tournamentRepository = new TournamentRepository(getTournamentService());
        }
        return tournamentRepository;
    }

    public static IParticipantRepository getParticipantRepository() {
        if (participantRepository == null) {
            participantRepository = new ParticipantRepository(getParticipantService());
        }
        return participantRepository;
    }

    public static IMatchRepository getMatchRepository() {
        if (matchRepository == null) {
            matchRepository = new MatchRepository(getMatchService());
        }
        return matchRepository;
    }

    public static TournamentService getTournamentService() {
        if (tournamentService == null) {
            tournamentService = getRetrofit().create(TournamentService.class);
        }
        return tournamentService;
    }

    public static ParticipantService getParticipantService() {
        if (participantService == null) {
            participantService = getRetrofit().create(ParticipantService.class);
        }
        return participantService;
    }

    public static MatchService getMatchService() {
        if (matchService == null) {
            matchService = getRetrofit().create(MatchService.class);
        }
        return matchService;
    }

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addNetworkInterceptor(new StethoInterceptor())
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://apichamps.herokuapp.com/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();
        }
        return retrofit;
    }

    public static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    public static void setContext(Context context) {
        applicationContext = context;
    }
}
