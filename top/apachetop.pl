#!/usr/bin/perl
use warnings;
use FindBin qw($RealBin);

MAIN: {
    die "Please, specify server names. ex)apachetop.pl \"server1,server2.\"\n"
    if @ARGV < 1;

    my %rps_hash;
    my %busy_hash;
    my %wait_hash;
    my %ka_hash;
    my $rps_sum  = 0;
    my $wait_sum = 0;
    my @hostlist = split(/,/, $ARGV[0]);

    foreach $server_name (@hostlist) {
        my @result = extract_status($server_name);
        $rps_hash{$server_name}  = trim($result[0]) eq "" ? 0 : $result[0];
        $busy_hash{$server_name} = trim($result[1]) eq "" ? 0 : $result[1];
        $wait_hash{$server_name} = trim($result[2]) eq "" ? 0 : $result[2];
        $ka_hash{$server_name} = trim($result[3]) eq "" ? 0 : $result[3];
    }

    system "clear";
    print "=============================================================\n";
    printf "%15s %5s %5s %5s %5s\n", "SERVER", "RPS", "BUSY", "WAIT", "KA";
    print "=============================================================\n";
    foreach my $each (@hostlist) {
        $rps_sum += $rps_hash{$each};
        $wait_sum += $wait_hash{$each};
        printf "%15s %5s %5s %5s %5s\n",
          $each,
          $rps_hash{$each},
          $busy_hash{$each},
          $wait_hash{$each},
          $ka_hash{$each};
    }

    print "=============================================================\n";
    print "Summary\n";
    print "=============================================================\n";
    printf "%15s = %5s\n", "RPS(avg)",
      $rps_sum eq 0 ? 0 : int($rps_sum / scalar @hostlist);
    printf "%15s = %5s\n", "Waiting(sum)", $wait_sum;
}

sub extract_status {
    my $url  = "http://" . $_[0] . "/server-status?auto";
    my $html = `curl -s $url`;

    my $rps  = 0;
    my $busy = 0;
    my $waiting = 0;
    my $ka = 0;
    my @lines = split( /\n/, $html );
    my @result;

    for my $line (@lines) {
        @temp = split(/:/, $line );
        $rps = (split(/\./, $temp[1]))[0] if $line =~ /^ReqPerSec:/;
        $busy = $temp[1] if $line =~ /^BusyWorkers:/;
        $waiting = char_count($temp[1], "W") if $line =~ /^Scoreboard:/;
        $ka = char_count($temp[1], "K") if $line =~ /^Scoreboard:/;
    }

    push(@result, ($rps, $busy, $waiting,$ka));
    return @result;
}

sub char_count {
    return scalar (split /$_[1]/, $_[0]) - 1;
}

sub trim {
    my $result = $_[0];
    $result =~ s/^\s+//;
    $result =~ s/\s+$//;
    return $result;
}
